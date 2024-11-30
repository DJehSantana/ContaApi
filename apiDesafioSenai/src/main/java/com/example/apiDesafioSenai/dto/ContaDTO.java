package com.example.apiDesafioSenai.dto;

import com.example.apiDesafioSenai.model.Conta;

import java.time.LocalDateTime;

public record ContaDTO(Integer idConta, LocalDateTime data, Long cpf, String conta ) {
    public ContaDTO(Conta model) {
        this(model.getIdConta(), model.getDataCriacao(), model.getCpf(), model.getConta());
    }

    public ContaDTO(Integer idConta, Long cpf, String conta) {
        this(idConta, null, cpf, conta);
    }
}
