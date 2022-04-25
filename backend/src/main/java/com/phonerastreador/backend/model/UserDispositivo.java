package com.phonerastreador.backend.model;

import java.util.regex.Matcher;

public class UserDispositivo {
    private String username;
    private String dispositivo;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDispositivo() {
        return this.dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public static UserDispositivo ofMatcher(Matcher matcher) {
        String username = matcher.group(1);
        String dispositivo = matcher.group(2);

        UserDispositivo userDispositivo = new UserDispositivo();
        userDispositivo.setDispositivo(dispositivo);
        userDispositivo.setUsername(username);
        return userDispositivo;
    }

}
