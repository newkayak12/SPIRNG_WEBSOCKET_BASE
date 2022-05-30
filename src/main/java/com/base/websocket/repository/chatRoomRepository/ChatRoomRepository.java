package com.base.websocket.repository.chatRoomRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
        Optional<ChatRoom> getChatRoomByChatRoomNo(Long chatroomNo);
}