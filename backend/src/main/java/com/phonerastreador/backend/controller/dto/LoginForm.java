package com.phonerastreador.backend.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String username;
    private String senha;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.username, this.senha);
    }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                "}";
    }
}
