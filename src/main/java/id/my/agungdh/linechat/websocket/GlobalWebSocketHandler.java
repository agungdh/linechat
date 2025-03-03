package id.my.agungdh.linechat.websocket;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalWebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        session.getAttributes().put("username", "User-" + sessionId);

        sessions.put(session.getId(), session);
        System.out.println("Client connected: " + session.getId());
        session.sendMessage(new TextMessage("Hello! Your session ID: " + session.getId()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String clientMessage = message.getPayload();
        System.out.println("Received: " + clientMessage);

        // Echo the message back to the client
        session.sendMessage(new TextMessage("Echo: " + clientMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {
        System.out.println("Client disconnected: " + session.getId() + " " + status);
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
}
