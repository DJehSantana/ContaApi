package com.example.apiDesafioSenai.repository;

import com.example.apiDesafioSenai.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CepRepository extends JpaRepository<Cep, Integer> {

    Optional<Cep> findFirstByCep(Integer cep);

    boolean existsByCep(Integer cep);
}
