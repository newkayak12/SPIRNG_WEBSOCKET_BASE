package com.base.websocket.service;

import com.base.websocket.component.configure.stomp.repository.dto.StompMessageContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, StompMessageContainer message){
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
