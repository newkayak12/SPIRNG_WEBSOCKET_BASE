package com.base.websocket.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private  Boolean isDeleted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDateTime regDate;
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDateTime modifiedDate;
}
