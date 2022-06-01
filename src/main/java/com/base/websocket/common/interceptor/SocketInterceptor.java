package com.base.websocket.common.interceptor;

import com.base.websocket.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SocketInterceptor implements ChannelInterceptor {
    private final SocketSections socketSections;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        switch (command){
            case SUBSCRIBE:
                String subscribeDestination = accessor.getDestination();
                String subscribeUuid = subscribeDestination.substring(subscribeDestination.lastIndexOf("/"));
                socketSections.subscribe(subscribeUuid);
                break;
            case UNSUBSCRIBE:
                String unSubscribeDestination = accessor.getMessageHeaders().get("simpSubscriptionId").toString();
                String unSubscribeUuid =  unSubscribeDestination.substring(unSubscribeDestination.lastIndexOf("/"));
                try {
                    socketSections.unSubscribe(unSubscribeUuid);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        return ChannelInterceptor.super.preSend(message, channel);
    }
}
