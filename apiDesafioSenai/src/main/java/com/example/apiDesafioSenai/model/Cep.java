package com.example.apiDesafioSenai.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name="cep")
@Entity(name = "cep")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCep;

    @NotNull(message = "CEP é obrigatório")
    private Integer cep;

    private String rua;

    private String cidade;

    private String estado;

}
