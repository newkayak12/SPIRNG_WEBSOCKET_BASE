package com.base.websocket.repository.chatRoomRepository;

import com.base.websocket.repository.userRepository.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
public class ChatRoomRepositoryImpl extends QuerydslRepositorySupport implements ChatRoomRepositoryCustom {

    public ChatRoomRepositoryImpl() {
        super(ChatRoom.class);
    }

}
