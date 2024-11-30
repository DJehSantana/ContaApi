package com.example.apiDesafioSenai.dto;

import com.example.apiDesafioSenai.model.Conta;

public record RequestContaDTO(Long cpf, String conta) {
    public RequestContaDTO(Conta conta) {
        this(conta.getCpf(), conta.getConta());
    }
}
