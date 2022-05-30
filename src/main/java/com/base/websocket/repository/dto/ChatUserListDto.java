package com.base.websocket.repository.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ChatUserListDto implements Serializable {
    private  Long chatUserListNo;
    private  ChatRoomDto chatRoom;
    private  UserDto user;
    private  Long lastIdx;
}
