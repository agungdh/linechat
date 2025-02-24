package id.my.agungdh.linechat.service;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class LineSignatureValidator {
    private final String channelSecret;

    public LineSignatureValidator(String channelSecret) {
        this.channelSecret = channelSecret;
    }

    public boolean validateSignature(String requestBody, String signatureHeader) throws NoSuchAlgorithmException, InvalidKeyException {
            // Generate HMAC SHA256 Signature
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(channelSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] computedHash = mac.doFinal(requestBody.getBytes(StandardCharsets.UTF_8));

            // Encode to Base64
            String computedSignature = Base64.encodeBase64String(computedHash);

            // Compare with received signature
            return computedSignature.equals(signatureHeader);
    }
}
