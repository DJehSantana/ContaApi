package com.example.apiDesafioSenai.dto;

import com.example.apiDesafioSenai.model.Movimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimentacaoDTO(Integer idMovimentacao, Integer idConta, LocalDateTime dataMovimentacao, String acao, BigDecimal valor) {

    public MovimentacaoDTO(Movimentacao entidade) {
        this(entidade.getIdMovimentacao(), entidade.getConta().getIdConta(), entidade.getDataMovimentacao(), entidade.getAcao(), entidade.getValor());
    }
}
