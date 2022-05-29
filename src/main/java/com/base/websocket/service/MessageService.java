package com.base.websocket.service;

import com.base.websocket.repository.messageRepository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
}
