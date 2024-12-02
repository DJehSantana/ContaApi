package com.example.apiDesafioSenai.controllers;

import com.example.apiDesafioSenai.dto.CepDTO;
import com.example.apiDesafioSenai.dto.RequestResponseCepDTO;
import com.example.apiDesafioSenai.repository.CepRepository;
import com.example.apiDesafioSenai.services.CepService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cep")
public class CepController {

    private final CepRepository repository;

    private final CepService service;


    @Autowired
    CepController(CepRepository repository, CepService service) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CepDTO>> getAll() {
        List<CepDTO> listaCeps = repository.findAll().stream()
                .map(CepDTO::new).collect(Collectors.toList());
        return  ResponseEntity.ok(listaCeps);
    }

    @GetMapping(path = "/{cep}")
    public ResponseEntity<RequestResponseCepDTO> getByCep(@PathVariable("cep") Integer cep) {
        RequestResponseCepDTO cepDTO = service.buscarPorCampoCep(cep);
        return ResponseEntity.ok(cepDTO);
    }

    @PostMapping
    public ResponseEntity<CepDTO> saveCep(@Valid @RequestBody RequestResponseCepDTO data) {
        CepDTO cepDTO =  service.cadastrarCep(data);

        return ResponseEntity.ok(cepDTO);
    }

}
