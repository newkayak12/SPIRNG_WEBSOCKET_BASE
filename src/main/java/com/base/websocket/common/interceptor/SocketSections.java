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
    private Map<String, List<MessageDto>> memory = new ConcurrentHashMap<>();
    private final MessageService messageService;

    public void subscribe(String uuid){
        if(Objects.isNull(memory.get(uuid))){
            List<MessageDto> list = new ArrayList<>();
            memory.put(uuid, list);
        }
    }
    public void unSubscribe(String uuid) throws ServiceException {
        List result = memory.get(uuid);
        if(Objects.isNull(result)){
                    throw new ServiceException(Exceptions.CRITICAL_ERROR);
        }
        if(messageService.saveMessages(uuid, result)>0){
            memory.remove(uuid);
        }
    }

    public void setMessage(String uuid, MessageDto msg ) throws ServiceException {
        List result = memory.get(uuid);
        if(Objects.isNull(result)){
            throw new ServiceException(Exceptions.CRITICAL_ERROR);
        }
        result.add(msg);
    }
}
