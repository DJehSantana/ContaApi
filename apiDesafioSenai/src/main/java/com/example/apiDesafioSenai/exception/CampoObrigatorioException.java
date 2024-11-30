package com.example.apiDesafioSenai.exception;

public class CampoObrigatorioException extends RuntimeException {

    public CampoObrigatorioException(String nomeCampo) {
        super(String.format("O campo %s é obrigatório", nomeCampo));
    }
}
