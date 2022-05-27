package com.base.websocket.repository.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long userNo;
    private final String userId;
}
