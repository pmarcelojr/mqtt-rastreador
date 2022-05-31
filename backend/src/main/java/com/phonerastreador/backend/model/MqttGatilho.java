package com.phonerastreador.backend.model;

public enum MqttGatilho {
    PING('p'),
    REGIAO_CIRCULAR('c'),
    REGIAO_AVISO('b'),
    RESPOSTA_REPORT('r'),
    MANUAL('u'),
    MOVIMENTO('t'),
    ATUALIZACAO('v'), 
    DESCONHECIDO('?');

    private final char tipo;

    private MqttGatilho(char tipo) {
        this.tipo = tipo;
    }

    public final char getTipo() {
        return this.tipo;
    }

    public static MqttGatilho fromChar(char gatilho) {
        switch (gatilho) {
            case 'p':
                return MqttGatilho.PING;
            case 'c':
                return MqttGatilho.REGIAO_CIRCULAR;
            case 'b':
                return MqttGatilho.REGIAO_AVISO;
            case 'r':
                return MqttGatilho.RESPOSTA_REPORT;
            case 'u':
                return MqttGatilho.MANUAL;
            case 't':
                return MqttGatilho.MOVIMENTO;
            case 'v':
                return MqttGatilho.ATUALIZACAO;
            default:
                return null;
        }
    }
}
