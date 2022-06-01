package com.base.websocket.common.interceptor;

import com.base.websocket.common.exception.Exceptions;
import com.base.websocket.common.exception.ServiceException;
import com.base.websocket.repository.dto.MessageDto;
import com.base.websocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
@Component
@RequiredArgsConstructor
public class SocketSections {
    private final MessageService messageService;
    public void setMessage( MessageDto msg ) throws ServiceException {
        if(Objects.isNull(msg.getUser())){
            throw new ServiceException(Exceptions.CRITICAL_ERROR);
        }
        if(Objects.isNull(msg.getChatRoom())){
            throw new ServiceException(Exceptions.CRITICAL_ERROR);
        }
        messageService.saveMessage(msg);
    }
}
