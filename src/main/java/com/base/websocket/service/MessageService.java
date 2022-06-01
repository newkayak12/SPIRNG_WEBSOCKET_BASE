package com.base.websocket.service;

import com.base.websocket.common.mapper.Mapper;
import com.base.websocket.repository.dto.MessageDto;
import com.base.websocket.repository.messageRepository.Message;
import com.base.websocket.repository.messageRepository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    public Integer saveMessages(String uuid, List<MessageDto> messageDtoList){
        List<Message> list =  new ArrayList<>();
        list = Mapper.modelMapping(messageDtoList, list);
        return messageRepository.saveAll(list).size();
    }
}
