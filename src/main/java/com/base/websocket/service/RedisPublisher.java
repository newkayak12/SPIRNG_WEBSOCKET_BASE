package com.base.websocket.service;

import com.base.websocket.component.configure.stomp.repository.ChatRepository;
import com.base.websocket.component.configure.stomp.repository.dto.StompMessageContainer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RedisPublisher {
    private final ExecutorService executorService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatRepository repository;
    private PublishSubject<StompMessageContainer> subject = PublishSubject.create();

    @PostConstruct
    public void beforeInitialized(){
        subject
        .map(this::pushAndAll)
        .subscribeOn(Schedulers.from(executorService))
        .subscribe(containers -> {
            if ( containers.size() >= 100 ) {
                //save
                repository.flushAll();
            }
        });
    }
    @PreDestroy
    public void beforeDestroy () {
        this.saveAndFlush();
    }

    public List<StompMessageContainer> pushAndAll(StompMessageContainer target) {
        repository.push(target);
        return repository.getAll();
    }
    public void saveAndFlush() {
        List<StompMessageContainer> list = repository.getAll();
        //save
        repository.flushAll();
    }
    public void saveRoomAndFlush( Long roomNo ) {
        List<StompMessageContainer> list = repository.getAll();
        List<StompMessageContainer> toSave = list.stream()
                                                 .filter(container -> container.getMsg().getRoomNo().equals(roomNo))
                                                 .collect(Collectors.toList());
        List<StompMessageContainer> toPersist = list.stream().filter( listOne -> toSave.stream()
                                                             .noneMatch( save -> save.getRoomNo().equals(listOne.getRoomNo())))
                                                             .collect(Collectors.toList());

        //save
        repository.pushAll(toPersist);
    }

    public void publish(ChannelTopic topic, StompMessageContainer message){
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
