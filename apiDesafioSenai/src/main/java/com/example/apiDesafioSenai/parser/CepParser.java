package com.example.apiDesafioSenai.parser;

import com.example.apiDesafioSenai.dto.CepDTO;
import com.example.apiDesafioSenai.dto.RequestResponseCepDTO;
import com.example.apiDesafioSenai.model.Cep;
import org.springframework.stereotype.Component;

@Component
public class CepParser {
    public Cep requestCepDTOToEntity(RequestResponseCepDTO dto) {
        Cep cep = new Cep();
        cep.setCep(dto.cep());
        cep.setRua(dto.rua());
        cep.setCidade(dto.cidade());
        cep.setEstado(dto.estado());
        return cep;
    }

    public RequestResponseCepDTO EntityToRequestCepDTO(Cep entity) {
        return new RequestResponseCepDTO(entity.getCep(), entity.getRua(), entity.getCidade(), entity.getEstado());
    }

    public CepDTO EntityToDTO(Cep entity) {
        return new CepDTO(entity);
    }
}
