package com.base.websocket.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatRoomDto implements Serializable {
    private  LocalDateTime regDate;
    private  LocalDateTime modifiedDate;
    private  Long chatRoomNo;
    private  String uuid;
    private  List<ChatUserListDto> userList;

    @Data
    public static class ChatUserListDto implements Serializable {
        private  Long chatUserListNo;
        private  UserDto user;
    }
}
