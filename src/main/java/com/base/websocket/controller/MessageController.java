package com.base.websocket.controller;

import com.base.websocket.repository.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    @MessageMapping("/chat/connected/{uuid}")
    public void connected(@DestinationVariable String uuid, @Payload MessageDto message){

    }
    @MessageMapping("/chat/disconnect/{uuid}")
    public void disconnect(@DestinationVariable String uuid, @Payload MessageDto message){

    }
    @MessageMapping("/chat/message/{uuid}")
    @SendTo("/sub/chat/message/{uuid}")
    public MessageDto chat(@DestinationVariable String uuid, @Payload MessageDto message){
      return message;
    }
}
