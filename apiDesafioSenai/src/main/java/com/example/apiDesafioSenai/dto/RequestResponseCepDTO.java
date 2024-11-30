package com.example.apiDesafioSenai.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestResponseCepDTO(
        @NotNull(message = "CEP não pode ser nulo")
        @Min(value = 10000000, message = "CEP deve ter 8 dígitos")
        @Max(value = 99999999, message = "CEP deve ter 8 dígitos")
        Integer cep,
        String rua,
        String cidade,
        String estado
) {

}
