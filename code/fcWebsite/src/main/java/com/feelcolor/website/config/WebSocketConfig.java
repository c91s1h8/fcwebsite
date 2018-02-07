package com.feelcolor.website.config;
import com.feelcolor.website.interceptor.WebSocketHandshakeInterceptor;
import com.feelcolor.website.socket.SystemWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(systemWebSocketHandler(),"/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor());

        webSocketHandlerRegistry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }
}