package com.phonerastreador.backend.controller.form;

import java.io.Serializable;

public class DispositivoForm implements Serializable {
    private String nome;
    private String usuario;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
