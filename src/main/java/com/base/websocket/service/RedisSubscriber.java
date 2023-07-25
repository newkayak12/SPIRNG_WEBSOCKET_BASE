package com.base.websocket.service;

import com.base.websocket.component.configure.stomp.repository.dto.StompMessageContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    /**
     * Redis에서 메세지가 발행(publish)되면 대기하고 있던 onMessage가 해당 메세지를 받아 처리
     */

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try{
            // redis에서 발행된 데이터를 받아 deserialize
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            // ChatMessage 객체로 매핑
            StompMessageContainer container = objectMapper.readValue(publishMessage, StompMessageContainer.class);
            // WebSocket 구독자에게 채팅 메세지 Send

            messagingTemplate.convertAndSend(
                    String.format("/topic/%s", container.getRoomNo()),
                    container.getMsg(),
                    container.getHeaders()
            );
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }


}