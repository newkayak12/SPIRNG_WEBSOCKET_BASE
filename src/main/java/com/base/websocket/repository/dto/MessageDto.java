package com.base.websocket.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageDto implements Serializable {
    private final LocalDateTime regDate;
    private final LocalDateTime modifiedDate;
    private final Long messageNo;
    private final ChatRoomDto chatRoom;
    private final UserDto user;
    private final String message;
    private final Boolean isDeleted;
}
