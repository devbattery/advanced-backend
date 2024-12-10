package com.lion.demo.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private EchoWebSocketHandler echoWebSocketHandler;
    private PersonalWebSocketHandler personalWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler, "/echo")
                .setAllowedOrigins("*");                // 모든 도메인에서 접근 가능

        registry.addHandler(personalWebSocketHandler, "/personal")
                .addInterceptors(new UserHandshakeInterceptor())        // 1 : 1 messaging
                .setAllowedOrigins("*");
    }

}
