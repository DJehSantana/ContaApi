package com.example.apiDesafioSenai.services;

import com.example.apiDesafioSenai.dto.*;
import com.example.apiDesafioSenai.exception.CampoObrigatorioException;
import com.example.apiDesafioSenai.exception.DataBaseException;
import com.example.apiDesafioSenai.exception.RegistroDuplicadoException;
import com.example.apiDesafioSenai.model.Conta;
import com.example.apiDesafioSenai.model.Movimentacao;
import com.example.apiDesafioSenai.parser.ContaParser;
import com.example.apiDesafioSenai.repository.ContaRepository;
import com.example.apiDesafioSenai.repository.MovimentacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    ContaParser contaParser;

    @Autowired
    MovimentacaoService movimentacaoService;

    public ResponseContaDTO cadastrarConta(RequestContaDTO contaDTO) {

        if(contaRepository.existsByConta(contaDTO.conta())) {
            throw new RegistroDuplicadoException("Número conta", contaDTO.conta());
        }

        Conta novaConta = new Conta();
        novaConta.setConta(contaDTO.conta());
        novaConta.setCpf(contaDTO.cpf());
        novaConta.setDataCriacao(LocalDateTime.now());

        String msg = "Conta de número: " + contaDTO.conta() + " cadastrada com sucesso!";

        return new ResponseContaDTO(contaRepository.save(novaConta), msg);
    }

    public Set<ResponseContaDTO> buscarContasPorCpf(Long cpf) {
        if(Objects.isNull(cpf)) return Collections.emptySet();

        List<Conta> contas = contaRepository.findByCpf(cpf);

        Set<ResponseContaDTO> responseContasDTO = new HashSet<ResponseContaDTO>();
        for(Conta conta: contas) {
            BigDecimal saldo = movimentacaoService.consultarSaldoConta(conta);
            responseContasDTO.add(new ResponseContaDTO(conta.getConta(), conta.getDataCriacao(), saldo));
        }

        return responseContasDTO;
    }

    public ResponseContaDTO apagarContaPorNumeroConta(String conta) {
        if(Objects.isNull(conta)) {
            throw new CampoObrigatorioException("CONTA");
        }

        if(!contaRepository.existsByConta(conta)) {
            return new ResponseContaDTO("Nenhuma conta encontrada com o número: " + conta);
        }

        Conta contaBD = contaRepository.findFirstByConta(conta).orElse(null);

        List<Movimentacao> movimentacoesConta = movimentacaoRepository.findMovimentacaosByConta(contaBD);

        if(!CollectionUtils.isEmpty(movimentacoesConta)) {
            try{
                movimentacaoRepository.deleteAll(movimentacoesConta);
            }catch(Exception e) {
                String msg = "Erro ao excluir movimentações da conta ";
                log.error(msg, e);
                throw  new DataBaseException(msg);
            }
        }
        contaRepository.deleteByConta(conta);

        return new ResponseContaDTO("Conta " + conta + " deletada com sucesso!");
    }

    public MovimentacoesContaDTO carregarMovimentacoesConta(String conta) {
        Conta contaBD = contaRepository.findFirstByConta(conta).orElse(null);

        if(Objects.isNull(contaBD)) return null;

        return movimentacaoService.buscarMovimentacoesPorConta(contaBD);
    }
}
