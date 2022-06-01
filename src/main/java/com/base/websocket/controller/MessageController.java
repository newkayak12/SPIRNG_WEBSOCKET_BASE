package com.base.websocket.controller;

import com.base.websocket.common.constants.Constants;
import com.base.websocket.common.exception.ServiceException;
import com.base.websocket.common.interceptor.SocketInterceptor;
import com.base.websocket.common.interceptor.SocketSections;
import com.base.websocket.repository.dto.ChatRoomDto;
import com.base.websocket.repository.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final SocketSections socketSections;

    @MessageMapping("/message/{uuid}")
    @SendTo("/sub/message/{uuid}")
    public MessageDto chat(@DestinationVariable String uuid, @Payload MessageDto message) throws ServiceException {
        socketSections.setMesssage(uuid, message);
        return message;
    }
}
