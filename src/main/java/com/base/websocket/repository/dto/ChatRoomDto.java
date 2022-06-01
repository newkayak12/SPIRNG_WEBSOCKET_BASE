package com.base.websocket.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ChatRoomDto implements Serializable {
    private  Long chatRoomNo;
    private  String chatRoomName;
    private  String uuid;
    @JsonIgnore
    private  List<ChatUserListDto> userList;
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDateTime regDate;
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDateTime modifiedDate;
}
