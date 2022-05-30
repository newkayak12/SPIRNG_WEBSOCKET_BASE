package com.base.websocket.repository.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ChatRoomInsertDto implements Serializable {
    private Long chatRoomNo;
    private String chatRoomName;
    private UserDto[] userDtoList;
}
