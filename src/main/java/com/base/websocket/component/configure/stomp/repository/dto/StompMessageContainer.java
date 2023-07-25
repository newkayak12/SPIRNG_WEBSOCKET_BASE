package com.base.websocket.component.configure.stomp.repository.dto;

import com.base.websocket.repository.dto.Bubble;
import lombok.*;

import java.util.Map;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class StompMessageContainer {
    private String roomNo;
    private Map<String, Object> headers;
    private Bubble msg;
}
