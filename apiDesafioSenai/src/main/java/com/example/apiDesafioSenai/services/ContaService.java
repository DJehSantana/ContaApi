package com.example.apiDesafioSenai.services;

import com.example.apiDesafioSenai.dto.CepDTO;
import com.example.apiDesafioSenai.dto.ContaDTO;
import com.example.apiDesafioSenai.dto.RequestContaDTO;
import com.example.apiDesafioSenai.dto.RequestResponseCepDTO;
import com.example.apiDesafioSenai.exception.RegistroDuplicadoException;
import com.example.apiDesafioSenai.model.Cep;
import com.example.apiDesafioSenai.model.Conta;
import com.example.apiDesafioSenai.parser.ContaParser;
import com.example.apiDesafioSenai.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ContaParser contaParser;

    public ContaDTO cadastrarConta(RequestContaDTO contaDTO) {

        if(contaRepository.existsByConta(contaDTO.conta())) {
            throw new RegistroDuplicadoException("Número conta", contaDTO.conta());
        }

        Conta novaConta = new Conta();
        novaConta.setConta(contaDTO.conta());
        novaConta.setCpf(contaDTO.cpf());

        return contaParser.EntityToDTO(contaRepository.save(novaConta));
    }

    public List<ContaDTO> buscarContasPorCpf(Long cpf) {
        if(Objects.isNull(cpf)) return Collections.emptyList();

        return contaRepository.findByCpf(cpf).stream()
                .map(ContaDTO::new).collect(Collectors.toList());
    }
}
