package com.example.apiDesafioSenai.services;

import com.example.apiDesafioSenai.constantes.AcaoEnum;
import com.example.apiDesafioSenai.dto.MovimentacaoDTO;
import com.example.apiDesafioSenai.dto.MovimentacoesContaDTO;
import com.example.apiDesafioSenai.dto.RequestMovimentacaoDTO;
import com.example.apiDesafioSenai.dto.ResponseMovimentacaoDTO;
import com.example.apiDesafioSenai.model.Conta;
import com.example.apiDesafioSenai.model.Movimentacao;
import com.example.apiDesafioSenai.repository.ContaRepository;
import com.example.apiDesafioSenai.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    private final ContaRepository contaRepository;

    @Autowired
    MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ContaRepository contaRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.contaRepository = contaRepository;
    }

    public MovimentacoesContaDTO buscarMovimentacoesPorConta(Conta conta) {
        MovimentacoesContaDTO movimentacoesContaDTO = new MovimentacoesContaDTO();
        if(Objects.isNull(conta)) return movimentacoesContaDTO;

        List<Movimentacao> movimentacoes = movimentacaoRepository.findMovimentacaosByConta(conta);

        if(CollectionUtils.isEmpty(movimentacoes)) return movimentacoesContaDTO;

        Set<ResponseMovimentacaoDTO> movimentacoesDTO = movimentacoes.stream()
                .map( movimentacao -> new ResponseMovimentacaoDTO(movimentacao.getDataMovimentacao(), movimentacao.getAcao(), movimentacao.getValor()))
                .collect(Collectors.toSet());

        movimentacoesContaDTO.setMovimentacoes(new ArrayList<>(movimentacoesDTO));

        return movimentacoesContaDTO;
    }

    public BigDecimal consultarSaldoConta(Conta conta) {
        BigDecimal saldo  = BigDecimal.ZERO;

        List<Movimentacao> movimentacoes = movimentacaoRepository.findMovimentacaosByConta(conta);

        if(CollectionUtils.isEmpty(movimentacoes)) return saldo ;

        for(Movimentacao movimentacao: movimentacoes) {
            if(movimentacao.getAcao().equals(AcaoEnum.DEPOSITAR.getDescricao())) {
                saldo = saldo.add(movimentacao.getValor());
            } else {
                saldo  = saldo.subtract(movimentacao.getValor());
            }
        }
        return saldo;
    }

    public ResponseMovimentacaoDTO cadastrarMovimentacaoConta(RequestMovimentacaoDTO reqMovimentacaoDTO) {

        validarCadastroMovimentacaoConta(reqMovimentacaoDTO);

        Conta contaBD = contaRepository.findFirstByConta(reqMovimentacaoDTO.conta()).orElse(new Conta());

        Movimentacao novaMovimentacao = new Movimentacao();
        novaMovimentacao.setDataMovimentacao(LocalDateTime.now());
        novaMovimentacao.setConta(contaBD);
        novaMovimentacao.setValor(reqMovimentacaoDTO.valor());
        novaMovimentacao.setAcao(reqMovimentacaoDTO.acao());

        return new ResponseMovimentacaoDTO(movimentacaoRepository.save(novaMovimentacao), "Nova movimentação cadastrada com sucesso!");
    }

    private void validarCadastroMovimentacaoConta(RequestMovimentacaoDTO requestMovimentacaoDTO) {
        if(!contaRepository.existsByConta(requestMovimentacaoDTO.conta())) {
            throw new IllegalArgumentException("Conta de número " + requestMovimentacaoDTO.conta() + " não encontrada");
        }

        if(!isValidAcaoEnum(requestMovimentacaoDTO.acao())) {
            throw new IllegalArgumentException("O campo ação não possui um valor válido");
        }
    }

    private boolean isValidAcaoEnum(String acao) {
        return Arrays.stream(AcaoEnum.values())
                .map(AcaoEnum::getDescricao)
                .anyMatch(a -> a.equals(acao));
    }
}
