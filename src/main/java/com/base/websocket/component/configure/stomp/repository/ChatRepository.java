package com.base.websocket.component.configure.stomp.repository;

import com.base.websocket.component.configure.stomp.repository.dto.StompMessageContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChatRepository {
    private ListOperations<String, List<StompMessageContainer>> container;
    private String CHAT_LIST_KEY = "list";

    @Autowired
    public ChatRepository(RedisTemplate<String, List<StompMessageContainer>> redisTemplate) {
        this.container = redisTemplate.opsForList();
        container.leftPush(CHAT_LIST_KEY, new ArrayList<>());
    }


    public List<StompMessageContainer> getAll(){
        return container.leftPop(CHAT_LIST_KEY);
    }
    public StompMessageContainer get( Integer index ){
        try {
            return this.getAll().get(index);
        } catch ( IndexOutOfBoundsException e ){
            return null;
        }
    }

    private void persist( List<StompMessageContainer> list ) {
        container.leftPush(CHAT_LIST_KEY, list);
    }

    public void push ( StompMessageContainer messageContainer ){
        List<StompMessageContainer> list = this.getAll();
        list.add(messageContainer);

        persist(list);
    }
    public void pushAll ( List<StompMessageContainer> list ) {
        persist(list);
    }

    public StompMessageContainer pop ( Integer index ) {
        List<StompMessageContainer> list = this.getAll();
        StompMessageContainer one = this.get(index);
        list.remove(index);
        persist(list);

        return one;
    }

    public void flushAll () {
        container.leftPush(CHAT_LIST_KEY, new ArrayList<>());
    }
}
