package com.base.websocket.repository.chatRoomRepository;

import com.base.websocket.common.baseEntity.DefaultDateEntity;
import com.base.websocket.repository.chatUserListRepository.ChatUserList;
import com.base.websocket.repository.userRepository.User;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "chat_room")
@Getter
@EqualsAndHashCode
@ToString
public class ChatRoom extends DefaultDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "chatroom_no")
    private Long chatRoomNo;
    @Column(name = "chatroom_name")
    private String chatRoomName;
    @Column(name = "uuid", unique = true)
    private String uuid;
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatUserList> userList = new ArrayList<>();

    public ChatRoom() {
        this.uuid = UUID.randomUUID().toString();
    }
    public void addUser(User... user){
        Arrays.stream(user).forEach(userPiece->{
            if(this.userList.stream().filter(item->item.getUser().equals(userPiece)).count()<=0){
                this.userList.add(ChatUserList.builder().chatRoom(this).lastIdx(0L).user(userPiece).build());
            }
        });
        this.chatRoomName = this.userList.stream().map(item->item.getUser().getUserId())
                .collect(Collectors.joining(", "));
    }
    public void editRoomName(String name){
        if(StringUtils.isEmpty(name)){
            this.chatRoomName = null;
        }
    }

}
