package com.example.apiDesafioSenai.controllers;

import com.example.apiDesafioSenai.dto.ContaDTO;
import com.example.apiDesafioSenai.dto.MovimentacoesContaDTO;
import com.example.apiDesafioSenai.dto.RequestContaDTO;
import com.example.apiDesafioSenai.dto.ResponseContaDTO;
import com.example.apiDesafioSenai.repository.ContaRepository;
import com.example.apiDesafioSenai.services.ContaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ContaController {

    @Autowired
    ContaService service;

    @Autowired
    ContaRepository repository;
    @Autowired
    private ContaService contaService;

    @GetMapping("contas")
    public ResponseEntity<List<ContaDTO>> getAll() {
        List<ContaDTO> listaContas = repository.findAll().stream()
                .map(ContaDTO::new).collect(Collectors.toList());
        return  ResponseEntity.ok(listaContas);
    }

    @GetMapping("contas/{cpf}")
    public ResponseEntity<Set<ResponseContaDTO>> getByCpf(@PathVariable("cpf") Long cpf) {
        Set<ResponseContaDTO> listaContas =  service.buscarContasPorCpf(cpf);
        return ResponseEntity.ok(listaContas);
    }

    @GetMapping("contas/{conta}/extrato")
    public ResponseEntity<MovimentacoesContaDTO> getExtratoConta(@PathVariable("conta") String conta) {
        MovimentacoesContaDTO movimentacoes = contaService.carregarMovimentacoesConta(conta);
        return ResponseEntity.ok(movimentacoes);
    }


    @PostMapping("conta")
    public ResponseEntity<ResponseContaDTO> saveConta(@Valid @RequestBody RequestContaDTO data) {
        ResponseContaDTO response =  service.cadastrarConta(data);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("conta/{conta}")
    public ResponseEntity<ResponseContaDTO> deleteConta(@PathVariable("conta") String conta) {
        ResponseContaDTO response = contaService.apagarContaPorNumeroConta(conta);
        return ResponseEntity.ok(response);
    }


}
