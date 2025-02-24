package id.my.agungdh.linechat.dto.linewebhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Message(
        String id,
        String type,
        String text
) {}
