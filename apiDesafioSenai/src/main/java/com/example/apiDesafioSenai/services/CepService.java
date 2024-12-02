package com.example.apiDesafioSenai.services;

import com.example.apiDesafioSenai.exception.CampoObrigatorioException;
import com.example.apiDesafioSenai.parser.CepParser;
import com.example.apiDesafioSenai.dto.CepDTO;
import com.example.apiDesafioSenai.dto.RequestResponseCepDTO;
import com.example.apiDesafioSenai.exception.RegistroDuplicadoException;
import com.example.apiDesafioSenai.model.Cep;
import com.example.apiDesafioSenai.repository.CepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CepService {

    private final CepParser parser;

    private final CepRepository cepRepository;

    @Autowired
    CepService( CepRepository cepRepository, CepParser parser) {
        this.cepRepository = cepRepository;
        this.parser = parser;
    }

    public CepDTO cadastrarCep(RequestResponseCepDTO reqCepDTO) {

        if(cepRepository.existsByCep(reqCepDTO.cep())) {
            throw new RegistroDuplicadoException("CEP", reqCepDTO.cep());
        }
        CepDTO respCepDTO;

        Cep novoCep = parser.requestCepDTOToEntity(reqCepDTO);
        String msg = "Cep " + novoCep.getCep() + " cadastrado com sucesso";
        respCepDTO = new CepDTO(cepRepository.save(novoCep), msg);

        return respCepDTO;
    }

    public RequestResponseCepDTO buscarPorCampoCep(Integer cep) {
        Cep resultCep = cepRepository.findFirstByCep(cep).orElse(null);

        if(Objects.isNull(resultCep)) return null;

        return parser.EntityToRequestCepDTO(resultCep);
    }
}
