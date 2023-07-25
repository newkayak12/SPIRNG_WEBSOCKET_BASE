package com.base.websocket.controller;

import com.base.websocket.component.configure.stomp.repository.dto.StompMessageContainer;
import com.base.websocket.repository.dto.BubbleDto;
import com.base.websocket.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final RedisPublisher redisPublisher;

    @MessageMapping(value = "/chat/{uuid}")
    public void chat(@DestinationVariable String uuid, @Headers Map<String,Object> headers, @Payload BubbleDto msg ){
       log.warn("convertAndSend {}, {}", String.format("/topic/%s", uuid), msg);
        redisPublisher.publish(
                new ChannelTopic(uuid),
                StompMessageContainer
                        .builder()
                        .roomNo(uuid)
                        .headers(headers)
                        .msg(msg)
                        .build()
                );
    }
}
