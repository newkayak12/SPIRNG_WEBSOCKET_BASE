package com.base.websocket.component.configure.stomp.repository.dto;

import lombok.*;

import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StompMessageContainer {
    private String uuid;
    private Map<String, Object> headers;
    private String msg;
}
