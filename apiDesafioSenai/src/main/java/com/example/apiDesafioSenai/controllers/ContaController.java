package com.example.apiDesafioSenai.controllers;

import com.example.apiDesafioSenai.dto.ContaDTO;
import com.example.apiDesafioSenai.dto.RequestContaDTO;
import com.example.apiDesafioSenai.repository.ContaRepository;
import com.example.apiDesafioSenai.services.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    ContaService service;

    @Autowired
    ContaRepository repository;

    @GetMapping
    public ResponseEntity<List<ContaDTO>> getAll() {
        List<ContaDTO> listaContas = repository.findAll().stream()
                .map(ContaDTO::new).collect(Collectors.toList());
        return  ResponseEntity.ok(listaContas);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<ContaDTO>> getByCpf(@PathVariable("cpf") Long cpf) {
        List<ContaDTO> listaContas =  service.buscarContasPorCpf(cpf);
        return ResponseEntity.ok(listaContas);
    }


    @PostMapping
    public ResponseEntity<ContaDTO> saveConta(@Valid @RequestBody RequestContaDTO data) {
        ContaDTO contaDTO =  service.cadastrarConta(data);

        return ResponseEntity.ok(contaDTO);
    }

    @DeleteMapping("{idConta}")
    public ResponseEntity<Object> deleteConta(@PathVariable("idConta") Integer idConta) {
        repository.deleteById(idConta);
        return ResponseEntity.ok("Registro deletado com sucesso");
    }


}
