package com.phonerastreador.backend.controller.dto;

import java.io.Serializable;

public class TokenDto implements Serializable {

    private String token;

    private String type;

    private Long expires;

    public TokenDto(String token, String type, Long expires) {
        this.token = token;
        this.type = type;
        this.expires = expires;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getExpires() {
        return this.expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

}
