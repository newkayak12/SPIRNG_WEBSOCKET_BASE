package com.base.websocket.repository.messageRepository;

import com.base.websocket.common.baseEntity.DefaultDateEntity;
import com.base.websocket.repository.chatRoomRepository.ChatRoom;
import com.base.websocket.repository.userRepository.User;
import lombok.*;

import javax.persistence.*;

@Table(name = "message")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Message extends DefaultDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNo;

    @ManyToOne
    @JoinColumn(name = "chatroom_no")
    private ChatRoom chatRoom;

    @OneToOne
    @JoinColumn(name = "sender", referencedColumnName = "user_no")
    private User user;

    @Column(name = "message", columnDefinition = "Text")
    private String message;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
