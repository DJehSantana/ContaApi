package com.example.apiDesafioSenai.dto;

import com.example.apiDesafioSenai.model.Cep;

public record CepDTO(Integer idCep, Integer cep, String rua, String cidade, String estado) {

    public CepDTO(Cep cep) {
        this(cep.getIdCep(), cep.getCep(), cep.getRua(), cep.getCidade(), cep.getEstado());
    }
}
