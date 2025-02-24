package id.my.agungdh.linechat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.agungdh.linechat.dto.LineWebhookDTO;
import id.my.agungdh.linechat.entity.ChatMessage;
import id.my.agungdh.linechat.repository.ChatMessageRepository;
import id.my.agungdh.linechat.service.ChatMessageService;
import id.my.agungdh.linechat.service.LineSignatureValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final LineSignatureValidator signatureValidator;
    private final ChatMessageService chatMessageService;
    private final ObjectMapper objectMapper;

    public WebhookController(LineSignatureValidator signatureValidator, ChatMessageService chatMessageService, ObjectMapper objectMapper) {
        this.signatureValidator = signatureValidator;
        this.chatMessageService = chatMessageService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<String> handleWebhook(HttpServletRequest request,
                                                @RequestHeader("X-Line-Signature") String signature)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        // Read and cache request body (so it can be used twice)
        String requestBody = new BufferedReader(request.getReader())
                .lines()
                .collect(Collectors.joining("\n"));

        // Validate Signature
        if (!signatureValidator.validateSignature(requestBody, signature)) {
            return ResponseEntity.status(401).body("Invalid signature");
        }

        // Convert request body to DTO manually
        LineWebhookDTO lineWebhookDTO = objectMapper.readValue(requestBody, LineWebhookDTO.class);

        // Process messages
        chatMessageService.handleWebhook(lineWebhookDTO);

        return ResponseEntity.ok("Webhook received and processed successfully!");
    }
}