package com.example.apiDesafioSenai.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovimentacoesContaDTO {
     private List<ResponseMovimentacaoDTO> movimentacoes;
}
