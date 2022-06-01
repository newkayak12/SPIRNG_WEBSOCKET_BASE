package com.base.websocket.repository.chatUserListRepository;

import com.base.websocket.repository.chatRoomRepository.ChatRoom;
import com.base.websocket.repository.userRepository.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = true, exclude = {"user", "chatroom"})
public class ChatUserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatUserListNo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chatroom_no")
    private ChatRoom chatRoom;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;
    @Column(name = "last_idx")
    private Long lastIdx;
}
