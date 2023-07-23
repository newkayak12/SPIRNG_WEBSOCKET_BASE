package com.base.websocket.component.configure.stomp.interceptor;

import com.base.websocket.component.configure.stomp.repository.StompRepository;
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
public class StompOutbound implements ChannelInterceptor {
    private final StompRepository repository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");
        String userNo = accessor.getFirstNativeHeader("userNo");

//        if ( command.equals(StompCommand.DISCONNECT) ) repository.detachUser(sessionId);

        log.warn("OUTBOUND ::: preSend ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        return ChannelInterceptor.super.preSend(message, channel);
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("OUTBOUND ::: postSend ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        ChannelInterceptor.super.postSend(message, channel, sent);
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("OUTBOUND ::: afterSendCompletion ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        ChannelInterceptor.super.afterSendCompletion(message, channel, sent, ex);
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        log.warn("OUTBOUND ::: preReceive ::: {} ", channel);
        return ChannelInterceptor.super.preReceive(channel);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("OUTBOUND ::: postReceive ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        return ChannelInterceptor.super.postReceive(message, channel);
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String connectionType = accessor.getFirstNativeHeader("connectType");
        String authorization = accessor.getFirstNativeHeader("authorization");

        log.warn("OUTBOUND ::: afterReceiveCompletion ::: {} /  {} / {} / {} ", command, sessionId, connectionType, authorization);
        ChannelInterceptor.super.afterReceiveCompletion(message, channel, ex);
    }
}
