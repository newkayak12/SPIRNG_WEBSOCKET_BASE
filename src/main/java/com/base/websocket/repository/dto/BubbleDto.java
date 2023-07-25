package com.base.websocket.repository.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BubbleDto {
    private Long chatNo;
    private Long roomNo;
    private Long userNo;
    private String msg;
    private LocalDateTime sentDate;
    private Boolean isRead;

}
