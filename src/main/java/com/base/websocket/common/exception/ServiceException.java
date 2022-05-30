package com.base.websocket.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception{
    private Integer code;
    private String msg;
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Exceptions exceptions) {
        super(exceptions.getMsg());
        this.code = exceptions.getCode();
        this.msg = exceptions.getMsg();
    }
}
