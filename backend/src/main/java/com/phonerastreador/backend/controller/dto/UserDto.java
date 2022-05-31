package com.phonerastreador.backend.controller.dto;

import com.phonerastreador.backend.model.User;

public class UserDto {
    private String nome;
    private String usuario;
    private String email;

    public UserDto(User user) {
        this.nome = user.getNome();
        this.usuario = user.getUsername();
        this.email = user.getEmail();
    }

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
