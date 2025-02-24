package id.my.agungdh.linechat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chat_messages")
public class ChatMessage {
    @Id
    private String id;
    private String sender;
    private String message;
    private long timestamp;
}
