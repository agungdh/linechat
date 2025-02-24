package id.my.agungdh.linechat.dto.linewebhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Event(
        String type,
        String mode,
        long timestamp,
        Source source,
        @JsonProperty("webhookEventId") String webhookEventId,
        @JsonProperty("deliveryContext") DeliveryContext deliveryContext,
        @JsonProperty("message") Message message,
        @JsonProperty("postback") Postback postback,
        @JsonProperty("joined") Joined joined,
        @JsonProperty("left") Left left
) {}
