package com.phonerastreador.backend.controller.form;

import java.io.Serializable;

public class NovaSenhaForm implements Serializable {
    private String senha;

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
