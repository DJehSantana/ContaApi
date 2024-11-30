package com.example.apiDesafioSenai.repository;

import com.example.apiDesafioSenai.model.Conta;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    boolean existsByConta(String conta);

    List<Conta> findByCpf(Long cpf);
}
