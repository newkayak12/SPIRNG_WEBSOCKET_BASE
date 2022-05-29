package com.base.websocket.service;

import com.base.websocket.common.baseDto.PagingContainer;
import com.base.websocket.common.baseDto.PagingDto;
import com.base.websocket.repository.chatRoomRepository.ChatRoomRepository;
import com.base.websocket.repository.chatUserListRepository.ChatUserList;
import com.base.websocket.repository.chatUserListRepository.ChatUserListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatUserListRepository chatUserListRepository;

    public PagingContainer<ChatUserList> getChatRoomList(Long userNo, PagingDto paging) {
        Pageable pageInfo = PageRequest.of(paging.getPage(), paging.getLimit(), Sort.by("chatUserListNo").descending());
        Page<ChatUserList> list =  chatUserListRepository.getChatRoomListByUserNo(userNo, pageInfo);
        return new PagingContainer<>(pageInfo, list);
    }
}
