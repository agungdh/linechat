package id.my.agungdh.linechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        @NotNull
        String username,
        @NotNull
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        @NotNull
        @Size(max = 3)
        String name
) {
}
