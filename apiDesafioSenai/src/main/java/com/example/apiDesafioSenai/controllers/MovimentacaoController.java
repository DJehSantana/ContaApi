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
/**
 * Controlador responsável pelas operações relacionadas a movimentações.
 * */
@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    private final MovimentacaoRepository movimentacaoRepository;

    private final MovimentacaoService movimentacaoService;
    /**
     * Construtor para injeção de dependências. *
     * @param movimentacaoService Serviço de movimentações.
     * @param movimentacaoRepository Repositório de movimentações.
     * */
    @Autowired
    MovimentacaoController(MovimentacaoService movimentacaoService, MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoService = movimentacaoService;
    }
    /**
     * Endpoint para obter todas as movimentações. *
     * @return ResponseEntity contendo um conjunto de DTOs de movimentações.
     * */
    @GetMapping
    public ResponseEntity<Set<MovimentacaoDTO>> getAll() {
        Set<MovimentacaoDTO> movimentacoes = movimentacaoRepository.findAll().stream()
                .map(MovimentacaoDTO::new).collect(Collectors.toSet());

        return ResponseEntity.ok(movimentacoes);
    }
    /**
     * Endpoint para salvar uma nova movimentação. *
     * @param reqMovimentacaoDTO DTO contendo os dados da movimentação a ser salva. *
     * @return ResponseEntity contendo o DTO da movimentação salva.
     * */
    @PostMapping
    public ResponseEntity<ResponseMovimentacaoDTO> save(@Valid @RequestBody RequestMovimentacaoDTO reqMovimentacaoDTO) {
        ResponseMovimentacaoDTO response = movimentacaoService.cadastrarMovimentacaoConta(reqMovimentacaoDTO);
        return ResponseEntity.ok(response);
    }


}
