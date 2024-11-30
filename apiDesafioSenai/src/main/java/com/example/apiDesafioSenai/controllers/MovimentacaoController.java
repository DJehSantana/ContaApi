package com.example.apiDesafioSenai.controllers;

import com.example.apiDesafioSenai.dto.MovimentacaoDTO;
import com.example.apiDesafioSenai.dto.RequestMovimentacaoDTO;
import com.example.apiDesafioSenai.dto.ResponseMovimentacaoDTO;
import com.example.apiDesafioSenai.repository.MovimentacaoRepository;
import com.example.apiDesafioSenai.services.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    MovimentacaoService movimentacaoService;

    @GetMapping
    public ResponseEntity<Set<MovimentacaoDTO>> getAll() {
        Set<MovimentacaoDTO> movimentacoes = movimentacaoRepository.findAll().stream()
                .map(MovimentacaoDTO::new).collect(Collectors.toSet());

        return ResponseEntity.ok(movimentacoes);
    }

    @PostMapping
    public ResponseEntity<ResponseMovimentacaoDTO> save(@Valid @RequestBody RequestMovimentacaoDTO reqMovimentacaoDTO) {
        ResponseMovimentacaoDTO response = movimentacaoService.cadastrarMovimentacaoConta(reqMovimentacaoDTO);
        return ResponseEntity.ok(response);
    }


}
