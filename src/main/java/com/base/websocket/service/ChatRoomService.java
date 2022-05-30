package com.base.websocket.service;

import com.base.websocket.common.baseDto.PagingContainer;
import com.base.websocket.common.baseDto.PagingDto;
import com.base.websocket.common.exception.Exceptions;
import com.base.websocket.common.exception.ServiceException;
import com.base.websocket.common.mapper.Mapper;
import com.base.websocket.repository.chatRoomRepository.ChatRoom;
import com.base.websocket.repository.chatRoomRepository.ChatRoomRepository;
import com.base.websocket.repository.chatUserListRepository.ChatUserList;
import com.base.websocket.repository.chatUserListRepository.ChatUserListRepository;
import com.base.websocket.repository.dto.ChatRoomDto;
import com.base.websocket.repository.dto.ChatRoomInsertDto;
import com.base.websocket.repository.dto.ChatUserListDto;
import com.base.websocket.repository.userRepository.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    public ChatUserListDto getChatRoom(Long userNo, String uuid) {
        ChatUserListDto dto = Mapper.modelMapping( chatUserListRepository.getChatRoomByUserNoAndUuid(userNo, uuid), new ChatUserListDto());
        return dto;
    }

    public ChatRoomDto saveChatRoom(ChatRoomInsertDto chatRoomInsertDto) {
        ChatRoom chatRoom = chatRoomRepository.getChatRoomByChatRoomNo(chatRoomInsertDto.getChatRoomNo()).orElseGet(ChatRoom::new);
        chatRoom.editRoomName(chatRoomInsertDto.getChatRoomName());
        chatRoom.addUser(Mapper.modelMapping(chatRoomInsertDto.getUserDtoList(), new User[chatRoomInsertDto.getUserDtoList().length]));
        chatRoom = chatRoomRepository.save(chatRoom);
        return Mapper.modelMapping(chatRoom, new ChatRoomDto());
    }

    public void deleteChatRoom(Long roomNo) throws ServiceException{
        chatRoomRepository.delete(chatRoomRepository.getChatRoomByChatRoomNo(roomNo).orElseThrow(()-> new ServiceException(Exceptions.NO_DATA)));
    }
}
