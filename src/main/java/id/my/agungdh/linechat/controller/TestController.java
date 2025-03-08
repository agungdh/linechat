package id.my.agungdh.linechat.controller;

import id.my.agungdh.linechat.websocket.GlobalWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final GlobalWebSocketHandler webSocketHandler;


    @GetMapping
    public ResponseEntity<Void> test() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/websocket")
    public ResponseEntity<Map<String, String>> getAllWebsocketSession() {
        Map<String, WebSocketSession> sessions = webSocketHandler.getSessions();

        Map<String, String> sessionIds = new HashMap<>();
        sessions.forEach((k, v) -> sessionIds.put(k, (String) v.getAttributes().get("auth")));

        return ResponseEntity.ok(sessionIds);
    }

    @PostMapping("/websocket")
    public ResponseEntity<Void> sendToWebsocket() throws IOException {
        List<WebSocketSession> sessions = webSocketHandler.getSessionsByAttribute("auth", "agungdh");

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage("tehee"));
            }
        }

        return ResponseEntity.noContent().build();
    }
}
