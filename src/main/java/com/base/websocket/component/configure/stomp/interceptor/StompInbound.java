package com.base.websocket.component.configure.stomp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StompInbound implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");


        log.warn("INBOUND ::: preSend ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        return ChannelInterceptor.super.preSend(message, channel);
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("INBOUND ::: postSend ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        ChannelInterceptor.super.postSend(message, channel, sent);
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("INBOUND ::: afterSendCompletion ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        ChannelInterceptor.super.afterSendCompletion(message, channel, sent, ex);
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        log.warn("INBOUND ::: preReceive ::: {} ", channel);
        return ChannelInterceptor.super.preReceive(channel);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("INBOUND ::: postReceive ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        return ChannelInterceptor.super.postReceive(message, channel);
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("INBOUND ::: afterReceiveCompletion ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        ChannelInterceptor.super.afterReceiveCompletion(message, channel, ex);
    }
}
