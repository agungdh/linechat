package id.my.agungdh.linechat.websocket;

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
        sessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) throws IOException {
        String clientMessage = message.getPayload();
        boolean closeSession = true;

        if (clientMessage.startsWith("AUTH:")) {
            String auth = clientMessage.substring(5).trim();
            if (!auth.isEmpty()) {
                session.getAttributes().put("auth", auth);
            }
        }

        if (session.getAttributes().get("auth") != null) {
            closeSession = false;
        }

        if (closeSession) {
            session.close();
        }

        if (session.isOpen()) {
            session.sendMessage(new TextMessage("Echo: " + clientMessage));
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
}
