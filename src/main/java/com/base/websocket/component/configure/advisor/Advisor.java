package com.base.websocket.component.configure.advisor;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class Advisor {

    @MessageExceptionHandler(value = {Exception.class})
    public Exception handleException(Exception e){
        return e;
    }
}
