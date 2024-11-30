package com.example.apiDesafioSenai.parser;

import com.example.apiDesafioSenai.dto.CepDTO;
import com.example.apiDesafioSenai.dto.ContaDTO;
import com.example.apiDesafioSenai.model.Cep;
import com.example.apiDesafioSenai.model.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaParser {
    public ContaDTO EntityToDTO(Conta entity) {
        return new ContaDTO(entity);
    }
}
