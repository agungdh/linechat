package id.my.agungdh.linechat.config;

import id.my.agungdh.linechat.service.ChatMessageService;
import id.my.agungdh.linechat.websocket.GlobalWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatMessageService chatMessageService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new GlobalWebSocketHandler(chatMessageService), "/ws").setAllowedOrigins("*");
    }
}
