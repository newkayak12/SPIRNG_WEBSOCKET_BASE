package com.base.websocket.repository.chatRoomRepository;

import com.base.websocket.common.baseEntity.DefaultDateEntity;
import com.base.websocket.repository.chatUserListRepository.ChatUserList;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat_room")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ChatRoom extends DefaultDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "chatroom_no")
    private Long chatRoomNo;
    @Column(name = "UUID", unique = true)
    private String uuid;
    @OneToMany(mappedBy = "chatRoom")
    private List<ChatUserList> userList;

}
