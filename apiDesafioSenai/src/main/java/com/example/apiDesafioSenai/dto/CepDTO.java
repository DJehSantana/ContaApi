package com.example.apiDesafioSenai.dto;

import com.example.apiDesafioSenai.model.Cep;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CepDTO(Integer id, Integer cep, String rua, String cidade, String estado, String mensagem) {
    public CepDTO(Cep cep) {
        this(cep.getIdCep(), cep.getCep(), cep.getRua(), cep.getCidade(), cep.getEstado(), null);
    }

    public CepDTO(Cep cep, String mensagem) {
        this(cep.getIdCep(), cep.getCep(), cep.getRua(), cep.getCidade(), cep.getEstado(), mensagem);
    }

    public CepDTO(String msg) {
        this(null,null, null, null, null, msg);
    }
}
