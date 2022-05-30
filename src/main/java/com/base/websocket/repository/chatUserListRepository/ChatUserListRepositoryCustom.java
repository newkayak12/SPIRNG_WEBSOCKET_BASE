package com.base.websocket.repository.chatUserListRepository;

import com.base.websocket.common.baseDto.PagingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChatUserListRepositoryCustom {
    Page<ChatUserList> getChatRoomListByUserNo(Long userNo, Pageable pageable);
    ChatUserList getChatRoomByUserNoAndUuid(Long userNo, String uuid);
}
