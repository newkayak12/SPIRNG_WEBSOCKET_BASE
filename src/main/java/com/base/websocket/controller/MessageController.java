package com.base.websocket.controller;

import com.base.websocket.common.exception.ServiceException;
import com.base.websocket.common.interceptor.SocketSections;
import com.base.websocket.repository.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final SocketSections socketSections;

    @MessageMapping("/message/{uuid}")
    @SendTo("/sub/message/{uuid}")
    public MessageDto chat(@DestinationVariable String uuid, @Payload MessageDto message) throws ServiceException {
        socketSections.setMessage(message);
        return message;
    }
}
