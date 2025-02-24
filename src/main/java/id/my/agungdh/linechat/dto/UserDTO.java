package id.my.agungdh.linechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.my.agungdh.linechat.validation.PasswordMatches;
import id.my.agungdh.linechat.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;

@PasswordMatches
public record UserDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        @NotBlank
        @UniqueUsername
        String username,
        @NotBlank
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        @NotBlank
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "password_confirmation")
        String confirmPassword,
        @NotBlank
        String name
) {
}
