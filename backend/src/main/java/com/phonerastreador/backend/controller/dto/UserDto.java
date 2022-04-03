package com.phonerastreador.backend.controller.dto;

import com.phonerastreador.backend.model.User;

public class UserDto {
    private String usuario;
    private String email;

    public UserDto(User user) {
        this.usuario = user.getUsername();
        this.email = user.getEmail();
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
