package id.my.agungdh.linechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        @NotBlank
        String username,
        @NotBlank
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        @NotBlank
        String name
) {
}
