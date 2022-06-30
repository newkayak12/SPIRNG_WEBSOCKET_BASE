package com.base.websocket.repository.SsEmitterRepository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class SseEmitterRepositoryImpl implements SseEmitterRepository{
    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();
    private final Map<String, Object> eventMap = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitterMap.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void saveEventCache(String emitterId, Object event) {
        eventMap.put(emitterId, event);;
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByUserNo(Long userNo) {
        return emitterMap.entrySet().stream().filter(entry->entry.getKey().startsWith(userNo.toString()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Object> findAllEventCacheStartWithByUserNo(Long userNo) {
        return eventMap.entrySet().stream().filter(entry->entry.getKey().startsWith(userNo.toString()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(String emitterId) {
        this.emitterMap.remove(emitterId);
    }

    @Override
    public void deleteAllEmitterStartWithUserNo(Long userNo) {
        emitterMap.forEach((key,emitter)->{
            if(key.startsWith(userNo.toString())){
                emitterMap.remove(key);
            }
        });
    }

    @Override
    public void deleteAllCacheStartWithUserNo(Long userNo) {
        eventMap.forEach((key,cache)->{
            if(key.startsWith(userNo.toString())){
                eventMap.remove(key);
            }
        });
    }
}
