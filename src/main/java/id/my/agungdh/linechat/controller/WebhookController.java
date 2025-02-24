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
    @Value("${id.my.agungdh.linechat.line.channel-secret}")
    private String channelSecret;
    private final LineSignatureValidator signatureValidator = new LineSignatureValidator(channelSecret);

    @PostMapping
    public String handleWebhook(HttpServletRequest request, @RequestHeader("X-Line-Signature") String signature) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        StringBuilder requestBody = new StringBuilder();

        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println(channelSecret);

        // Validate Signature
        if (!signatureValidator.validateSignature(requestBody.toString(), signature)) {
            return "Invalid signature";
        }

        // Process the LINE webhook event here...
        return "Webhook received successfully!";
    }
}
