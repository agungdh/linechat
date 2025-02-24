package id.my.agungdh.linechat.dto.linewebhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DeliveryContext(
        boolean isRedelivery
) {
}
