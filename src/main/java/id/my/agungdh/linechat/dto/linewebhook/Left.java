package id.my.agungdh.linechat.dto.linewebhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Left(
        List<Source> members
) {
}
