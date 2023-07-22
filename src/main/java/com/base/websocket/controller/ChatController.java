package com.base.websocket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/{uuid}")
    public void chat(@DestinationVariable String uuid, @Payload String msg ){
       log.warn("converAndSend {}, {}", String.format("/topic/%s", uuid), msg);
       template.convertAndSend(String.format("/topic/%s", uuid), msg);
    }
}
