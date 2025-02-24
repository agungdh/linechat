package id.my.agungdh.linechat.controller;

import id.my.agungdh.linechat.dto.UserDTO;
import id.my.agungdh.linechat.service.LineSignatureValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final LineSignatureValidator signatureValidator;

    // Constructor-based Dependency Injection
    public WebhookController(LineSignatureValidator signatureValidator) {
        this.signatureValidator = signatureValidator;
    }

    @PostMapping
    public ResponseEntity<String> handleWebhook(HttpServletRequest request, @RequestHeader("X-Line-Signature") String signature)
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

        // Process the LINE webhook event...
        return ResponseEntity.ok("Webhook received successfully!");
    }
}