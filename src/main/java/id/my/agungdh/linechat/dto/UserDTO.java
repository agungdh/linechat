package id.my.agungdh.linechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        String username,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        String name
) {
}
