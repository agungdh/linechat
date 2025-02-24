package id.my.agungdh.linechat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LineWebhookDTO(
        String destination,
        List<Event> events
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Event(
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

@JsonIgnoreProperties(ignoreUnknown = true)
record Source(
        String type,
        String userId,
        String groupId,
        String roomId
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Message(
        String id,
        String type,
        String text
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Postback(
        String data
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Joined(
        List<Source> members
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Left(
        List<Source> members
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record DeliveryContext(
        boolean isRedelivery
) {}
