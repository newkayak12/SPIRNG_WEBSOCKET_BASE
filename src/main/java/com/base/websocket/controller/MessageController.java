package com.base.websocket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/chat/message/{roomNo}")
    @SendTo("/sub/chat/message/{roomNo}")
    public Map<String,Object> chat(@DestinationVariable String roomNo, @Payload Map<String,Object> map){
      log.warn("roomNo, payload {}, {}", roomNo, map);
      return map;
    }
}
