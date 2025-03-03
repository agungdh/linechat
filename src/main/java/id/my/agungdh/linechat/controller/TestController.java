package id.my.agungdh.linechat.controller;

import id.my.agungdh.linechat.dto.LineWebhookDTO;
import id.my.agungdh.linechat.websocket.GlobalWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
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
        sessions.forEach((k, v) -> sessionIds.put(k, v.getAttributes().toString()));

        return ResponseEntity.ok(sessionIds);
    }
}
