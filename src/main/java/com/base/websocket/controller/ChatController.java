package com.base.websocket.controller;

import com.base.websocket.component.configure.stomp.repository.dto.StompMessageContainer;
import com.base.websocket.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final RedisPublisher redisPublisher;
//    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/{uuid}")
    public void chat(@DestinationVariable String uuid, @Headers Map<String,Object> headers, @Payload String msg ){
       log.warn("convertAndSend {}, {}", String.format("/topic/%s", uuid), msg);
//       template.convertAndSend(String.format("/topic/%s", uuid), msg, headers);
        redisPublisher.publish(
                new ChannelTopic(uuid),
                StompMessageContainer
                        .builder()
                        .uuid(uuid)
                        .headers(headers)
                        .msg(msg)
                        .build()
                );
    }
}
