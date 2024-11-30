package com.example.apiDesafioSenai.model;

import com.example.apiDesafioSenai.constantes.AcaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name="movimentacao")
@Entity(name = "movimentacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idMovimentacao;

    @ManyToOne
    @JoinColumn(name = "idConta", nullable = false)
    private Conta conta;

    private LocalDateTime dataMovimentacao;

    private String acao;

    private BigDecimal valor;
}
