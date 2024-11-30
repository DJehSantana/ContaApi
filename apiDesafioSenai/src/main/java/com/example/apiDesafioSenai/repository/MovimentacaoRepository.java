package com.example.apiDesafioSenai.repository;

import com.example.apiDesafioSenai.model.Conta;
import com.example.apiDesafioSenai.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    List<Movimentacao> findMovimentacaosByConta(Conta conta);
}
