package com.base.websocket.repository.chatUserListRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserListRepository extends JpaRepository<ChatUserList, Long> , ChatUserListRepositoryCustom{

}