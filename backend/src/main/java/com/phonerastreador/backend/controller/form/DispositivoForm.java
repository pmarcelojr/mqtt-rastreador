package com.phonerastreador.backend.controller.form;

import java.io.Serializable;

public class DispositivoForm implements Serializable {
    private String nome;
    private String senha;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
