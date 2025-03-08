package id.my.agungdh.linechat.websocket;

import id.my.agungdh.linechat.entity.ChatMessage;
import id.my.agungdh.linechat.service.ChatMessageService;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class GlobalWebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ChatMessageService chatMessageService;

    public GlobalWebSocketHandler(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) throws IOException {
        String clientMessage = message.getPayload();

        // check if it already authenticated
        if (!session.getAttributes().containsKey("auth")) {
            if (clientMessage.startsWith("AUTH:")) {
                String auth = clientMessage.substring(5).trim();
                if (!auth.isEmpty()) {
                    session.getAttributes().put("auth", auth);
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage("AUTH:success"));

                        List<ChatMessage> chatMessages = chatMessageService.getChatMessages();
                        System.out.println(chatMessages);
                        for (ChatMessage chatMessage : chatMessages) {
                            session.sendMessage(new TextMessage(chatMessage.toString()));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {
        sessions.remove(session.getId());
    }

    public void sendToSession(String sessionId, String message) throws IOException {
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }

    public List<WebSocketSession> getSessionsByAttribute(String key, String value) {
        return sessions.values().stream()
                .filter(session -> value.equals(session.getAttributes().get(key)))
                .collect(Collectors.toList());
    }
}
