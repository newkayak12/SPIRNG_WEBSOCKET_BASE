package com.base.websocket.component.configure.stomp;

import com.base.websocket.component.ConfigMsg;
import com.base.websocket.component.configure.stomp.interceptor.StompInbound;
import com.base.websocket.component.configure.stomp.interceptor.StompOutbound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration(value = "stomp_config")
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {
    private StompInbound preHandler;
    private StompOutbound afterHandler;

    @Autowired
    public Config() {
        preHandler = new StompInbound();
        afterHandler = new StompOutbound();
        ConfigMsg.msg("STOMP");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpoint").setAllowedOriginPatterns("*").withSockJS();
        registry.addEndpoint("/endpoint").setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(preHandler);
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(afterHandler);
    }


}
