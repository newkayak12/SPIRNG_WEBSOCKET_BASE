package com.base.websocket.service;

import com.base.websocket.common.baseDto.PagingContainer;
import com.base.websocket.common.baseDto.PagingDto;
import com.base.websocket.common.mapper.Mapper;
import com.base.websocket.repository.dto.MessageDto;
import com.base.websocket.repository.messageRepository.Message;
import com.base.websocket.repository.messageRepository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    public void saveMessage(MessageDto messageDto){
        Message msg = Mapper.modelMapping(messageDto, new Message());
        messageRepository.save(msg);
    }

    public PagingContainer chatRoomMessages(String uuid, PagingDto paging) {
        Pageable pageable = PageRequest.of(paging.getPage(), paging.getLimit(), Sort.by("messageNo").descending());
        Page<Message> list = messageRepository.getMessages(uuid, pageable);
        List<Message> msgList = list.getContent();
        Collections.reverse(msgList);
        list = new PageImpl<>(msgList, pageable, list.getTotalElements());
        return new PagingContainer(pageable,list);
    }
}
