package com.base.websocket.service;

import com.base.websocket.repository.SsEmitterRepository.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SseEmitterRepository sseEmitterRepository;
    private final Long timeOut = 60*1000*60*24L;

    /**
     * 이벤트 구독
     * <pre>
     *     1. emitterId를 만들고 구독을 한다.
     *     2. 해당 내용을 concurrentMap에 저장
     *     3. SSE 통신이 끝나면, 타임아웃이면 emitter를 삭제
     *     4. 503에러 발생 방지를 위한 더미 이벤트 발송
     *     5. 미수신 이벤트 존재할 경우 이벤트 발송
     * </pre>
     * @param userNo
     * @param lastEventId
     * @return
     */
    public SseEmitter subscribeEvent(Long userNo, String lastEventId) {
//        emitter 구분을 위한 아이디 만들고
        String emitterId = createTimedId(userNo);
//        concurrentMap에 이를 저장
        SseEmitter emitter = sseEmitterRepository.save(emitterId, new SseEmitter(timeOut));

//        만약 통신 두절이거나 타임아웃이면 concurrentMap에서 삭제 (이벤트만 남는 상황)
        emitter.onCompletion(()->sseEmitterRepository.deleteById(emitterId));
        emitter.onTimeout(()->sseEmitterRepository.deleteById(emitterId));

//        503에러 방지를 위한 더미 이벤트
        String eventId = createTimedId(userNo);
        remindNotifications(emitter, eventId, emitterId, "EventStream Created. [userNo="+userNo+"]");


//       미수신 이벤트가 존재할 경우 전송
        if(hasLostData(lastEventId)){
            sendLostEventData(lastEventId, userNo, emitterId, emitter);
        }
        return emitter;
    }

    /**
     * 알람 보내기
     * @param targetUserNo
     */
    public <T> void sendNotification(Long targetUserNo, T data ){
        String eventId = createTimedId(targetUserNo);
        Map<String,SseEmitter> emitterMap = sseEmitterRepository.findAllEmitterStartWithByUserNo(targetUserNo);
        emitterMap.forEach((key,emitter)->{
            sseEmitterRepository.saveEventCache(key, data);
            remindNotifications(emitter, eventId, key, data);
        });
    }


    /**
     * emitterId 생성
     * @param userNo
     * @return
     */
    private String createTimedId(Long userNo){
        return userNo+"_"+System.currentTimeMillis();
    }

    /**
     * 알람 받기
     * @param emitter
     * @param eventId
     * @param emitterId
     * @param data
     */
    private void remindNotifications(SseEmitter emitter,String eventId,  String emitterId, Object data){
        try{
            emitter.send(SseEmitter.event().id(eventId).data(data));
        }catch(IOException e){
            e.printStackTrace();
            sseEmitterRepository.deleteById(emitterId);
        }
    }

    /**
     * 유실 데이터 있는지 여부 확인 (lastEventId는 브라우저가 보내준다고 하는데...)
     * @param lastEventId
     * @return
     */
    private boolean hasLostData(String lastEventId){
        return !lastEventId.isEmpty();
    }

    /**
     * 유실 데이터 받기
     * @param lastEventId
     * @param userNo
     * @param emitterId
     * @param emitter
     */
    private void sendLostEventData(String lastEventId, Long userNo, String emitterId, SseEmitter emitter){
        Map<String,Object> eventCached = sseEmitterRepository.findAllEventCacheStartWithByUserNo(userNo);
        eventCached.entrySet().stream().filter(entry->lastEventId.compareTo(entry.getKey())<0)
                .forEach(entry -> remindNotifications(emitter, entry.getKey(), emitterId, entry.getValue()));
    }
}
