package com.base.websocket.controller;

import com.base.websocket.common.constants.Constants;
import com.base.websocket.repository.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    @MessageMapping("/connected/{uuid}")
    public void connected(@DestinationVariable String uuid, @Payload MessageDto message){

    }
    @MessageMapping("/disconnect/{uuid}")
    public void disconnect(@DestinationVariable String uuid, @Payload MessageDto message){

    }
    @MessageMapping("/chat/message/{uuid}")
    @SendTo("/sub/chat/message/{uuid}")
    public void chat(@DestinationVariable String uuid, @Payload MessageDto message){
        simpMessageSendingOperations.convertAndSend(Constants.SUBSCRIBE+"/message/"+uuid, message);
    }
}
