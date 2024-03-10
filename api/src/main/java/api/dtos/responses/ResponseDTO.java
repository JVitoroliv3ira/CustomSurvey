package api.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record ResponseDTO<C>(
        C content,
        String message,
        Map<String, List<String>> errors
) {
    @JsonSerialize
    public boolean hasErrors() {
        return Objects.nonNull(errors()) && !errors().isEmpty();
    }
}
