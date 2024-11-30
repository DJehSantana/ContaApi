package com.example.apiDesafioSenai.dto;

import com.example.apiDesafioSenai.model.Conta;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseContaDTO(Integer id, Long cpf, String conta, LocalDateTime data, BigDecimal saldo, String mensagem)  {

    public ResponseContaDTO(Conta conta, String mensagem ) {
        this(conta.getIdConta(), conta.getCpf(), conta.getConta(), null, null,  mensagem);
    }

    public ResponseContaDTO(String conta,  LocalDateTime data, BigDecimal saldo){
        this(null, null, conta, data, saldo, null);
    }

    public ResponseContaDTO(String mensagem) {
        this(null, null, null, null, null, mensagem);
    }
}

