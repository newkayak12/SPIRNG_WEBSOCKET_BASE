package com.base.websocket.repository.SsEmitterRepository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface SseEmitterRepository {
//    Emitter 저장
    SseEmitter save(String emitterId, SseEmitter sseEmitter);

//    이벤트를 저장
    void saveEventCache(String emitterId, Object event);

//    해당 회원과 관련된 Emitter를 찾기
    Map<String, SseEmitter> findAllEmitterStartWithByUserNo(Long userNo);

//    해당 회원과 관련된 몬든 이벤트를 찾는다.
    Map<String, Object> findAllEventCacheStartWithByUserNo(Long userNo);

//    Emitter를 지운다.
    void deleteById(String emitterId);

//    해당 회원과 관련된 모든 Emitter를 지운다.
    void deleteAllEmitterStartWithUserNo(Long userNo);

//    해당 회원과 관련된 모든 이벶트를 지운다.
    void deleteAllCacheStartWithUserNo(Long userNo);
}
