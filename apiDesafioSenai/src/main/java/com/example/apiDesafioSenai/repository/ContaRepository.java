package com.example.apiDesafioSenai.repository;

import com.example.apiDesafioSenai.model.Conta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    boolean existsByConta(String conta);

    List<Conta> findByCpf(Long cpf);

    Optional<Conta> findFirstByConta(String conta);

    @Modifying
    @Transactional
    @Query("DELETE FROM conta c WHERE c.conta = :conta")
    void deleteByConta(@Param("conta") String conta);
}
