package id.my.agungdh.linechat.repository;

import id.my.agungdh.linechat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
