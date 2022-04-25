package com.phonerastreador.backend.model;

public enum BateriaStatus {
    DESCONHECIDO(0),
    CONECTADO(1),
    CARREGANDO(2),
    CHEIA(3);

    private final Integer codigo;

    private BateriaStatus(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public static BateriaStatus fromInteger(Integer bateriaStatus) {
        switch (bateriaStatus) {
            case 0:
                return BateriaStatus.DESCONHECIDO;
            case 1:
                return BateriaStatus.CONECTADO;
            case 2:
                return BateriaStatus.CARREGANDO;
            case 3:
                return BateriaStatus.CHEIA;
            default:
                return null;
        }
    }
}
