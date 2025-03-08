package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.dto.LineWebhookDTO;
import id.my.agungdh.linechat.entity.ChatMessage;
import id.my.agungdh.linechat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public void handleWebhook(LineWebhookDTO lineWebhookDTO) {
        // Convert incoming events to ChatMessage entities
        List<ChatMessage> chatMessageList = lineWebhookDTO.events().stream()
                .filter(event -> "message".equals(event.type()) && event.message() != null)
                .map(event -> {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setSender(event.source().userId());
                    chatMessage.setMessage(event.message().text());
                    chatMessage.setTimestamp(Instant.now().toEpochMilli());

                    return chatMessage;
                })
                .collect(Collectors.toList());

        chatMessageRepository.saveAll(chatMessageList); // Use saveAll() for batch insert
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessageRepository.findAllByOrderByTimestampDesc();
    }
}