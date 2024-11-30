package com.example.apiDesafioSenai.constantes;

public enum AcaoEnum {
    DEPOSITAR("Depositar"),
    RETIRAR("Retirar");

    private final String descricao;

    AcaoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    // Método de conversão de String para Enum
    public static AcaoEnum fromString(String value) {
        for (AcaoEnum acao : values()) {
            if (acao.descricao.equalsIgnoreCase(value)) {
                return acao;
            }
        }
        throw new IllegalArgumentException("Ação inválida: " + value);
    }
}
