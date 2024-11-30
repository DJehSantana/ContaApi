package com.example.apiDesafioSenai.services;

import com.example.apiDesafioSenai.parser.CepParser;
import com.example.apiDesafioSenai.dto.CepDTO;
import com.example.apiDesafioSenai.dto.RequestResponseCepDTO;
import com.example.apiDesafioSenai.exception.RegistroDuplicadoException;
import com.example.apiDesafioSenai.model.Cep;
import com.example.apiDesafioSenai.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CepService {

    @Autowired
    CepParser parser;

    @Autowired
    private CepRepository cepRepository;

    public CepDTO cadastrarCep(RequestResponseCepDTO cepDTO) {

        if(cepRepository.existsByCep(cepDTO.cep())) {
            throw new RegistroDuplicadoException("CEP", cepDTO.cep());
        }

        Cep novoCep = parser.requestCepDTOToEntity(cepDTO);
        return parser.EntityToDTO(cepRepository.save(novoCep));
    }

    public RequestResponseCepDTO buscarPorCampoCep(Integer cep) {
        Cep resultCep = cepRepository.findFirstByCep(cep).orElse(null);

        if(Objects.isNull(resultCep)) return null;

        return parser.EntityToRequestCepDTO(resultCep);
    }
}
