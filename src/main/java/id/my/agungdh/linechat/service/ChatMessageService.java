package id.my.agungdh.linechat.service;

import id.my.agungdh.linechat.entity.ChatMessage;
import id.my.agungdh.linechat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage create() {
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setSender("Surimbim");
        chatMessage.setMessage("Raka nigga");
        chatMessage.setTimestamp(System.currentTimeMillis());


        return chatMessageRepository.save(chatMessage);
    }
}
