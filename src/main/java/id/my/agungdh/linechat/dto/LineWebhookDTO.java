package id.my.agungdh.linechat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import id.my.agungdh.linechat.dto.linewebhook.Event;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LineWebhookDTO(
        String destination,
        List<Event> events
) {}

