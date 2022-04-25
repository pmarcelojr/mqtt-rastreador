package com.phonerastreador.backend.model;

public enum TipoConexao {
    WIFI('w'),
    OFFLINE('o'),
    DADOS_MOVEIS('m');

    private final char tipo;

    private TipoConexao(char tipo) {
        this.tipo = tipo;
    }

    public final char getTipo() {
        return this.tipo;
    }

    public static TipoConexao fromChar(char conexao) {
        switch (conexao) {
            case 'w':
                return TipoConexao.WIFI;
            case 'o':
                return TipoConexao.OFFLINE;
            case 'm':
                return TipoConexao.DADOS_MOVEIS;
            default:
                return null;
        }
    }
}
