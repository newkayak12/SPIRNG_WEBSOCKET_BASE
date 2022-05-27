package com.base.websocket.repository.ChatRepository;

import com.base.websocket.repository.UserRepository.User;

import javax.persistence.*;

@Entity
@Table
public class ChatUserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatUserListNo;
    @ManyToOne
    @JoinColumn(name = "chatroom_no")
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;
    @Column(name = "last_idx")
    private Long lastIdx;
}
