package com.example.apiDesafioSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestMovimentacaoDTO(
        @NotBlank(message = "O número da conta não pode estar em branco")
        String conta,

        @NotBlank(message = "A ação não pode estar vazia")
        String acao,

        @NotNull(message = "O valor não pode ser nulo")
        BigDecimal valor
) {
}
