package com.base.websocket.repository.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class MessageDto implements Serializable {

    private  Long messageNo;
    private  ChatRoomDto chatRoom;
    private  UserDto user;
    private  String message;
    private  Boolean isDeleted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDateTime regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDateTime modifiedDate;
}
