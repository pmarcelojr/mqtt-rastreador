package com.phonerastreador.backend.controller.dto;

import java.io.Serializable;

public class ErroDto implements Serializable {
    private String mensagem;

    public ErroDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
