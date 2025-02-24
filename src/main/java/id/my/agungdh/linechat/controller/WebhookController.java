package id.my.agungdh.linechat.controller;

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

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final LineSignatureValidator signatureValidator;
    private final ChatMessageService chatMessageService;

    // Constructor-based Dependency Injection
    public WebhookController(LineSignatureValidator signatureValidator, ChatMessageService chatMessageService) {
        this.signatureValidator = signatureValidator;
        this.chatMessageService = chatMessageService;
    }

    @PostMapping
    public ResponseEntity<String> handleWebhook(
            HttpServletRequest request,
            @RequestHeader("X-Line-Signature") String signature,
            @RequestBody LineWebhookDTO lineWebhookDTO
    )
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Validate Signature
        if (!signatureValidator.validateSignature(requestBody.toString(), signature)) {
            return ResponseEntity.status(401).body("Invalid signature");
        }

        List<ChatMessage> chatMessageList = chatMessageService.handleWebhook(lineWebhookDTO);
        System.out.println(chatMessageList);

        // Process the LINE webhook event...
        return ResponseEntity.ok("Webhook received successfully!");
    }
}