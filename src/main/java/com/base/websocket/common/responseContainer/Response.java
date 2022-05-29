package com.base.websocket.common.responseContainer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response {
    private int code;
    private String msg;
    private Object data;
}
