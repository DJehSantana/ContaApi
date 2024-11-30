package com.example.apiDesafioSenai.dto;

import com.example.apiDesafioSenai.model.Movimentacao;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseMovimentacaoDTO(LocalDateTime data, String conta, String acao, BigDecimal valor, String mensagem) {
    public ResponseMovimentacaoDTO(Movimentacao entidade, String msg) {
        this(entidade.getDataMovimentacao(), entidade.getConta().getConta(), entidade.getAcao(), entidade.getValor(), msg);
    }

//    public ResponseMovimentacaoDTO(Movimentacao entidade, String msg) {
//
//    }

    public ResponseMovimentacaoDTO(LocalDateTime data, String acao, BigDecimal valor) {
        this(data, null, acao, valor, null );
    }
}
