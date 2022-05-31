package com.phonerastreador.backend.model;

public enum ModoMonitoramento {
    SIGNIFICATIVO(1),
    MOVIMENTO(2);

    private final Integer modo;

    private ModoMonitoramento(Integer modo) {
        this.modo = modo;
    }

    public final Integer getModo() {
        return this.modo;
    }

    public static ModoMonitoramento fromInteger(Integer modoMonitor) {
        switch (modoMonitor) {
            case 1:
                return ModoMonitoramento.SIGNIFICATIVO;
            case 2:
                return ModoMonitoramento.MOVIMENTO;
            default:
                return null;
        }
    }
}
