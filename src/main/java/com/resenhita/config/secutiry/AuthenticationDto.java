package com.resenhita.config.secutiry;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthenticationDto(
        @NotNull
        String email,
        @NotNull
        String senha
) {
}
