package com.phonerastreador.backend.controller.dto;

import com.phonerastreador.backend.model.Dispositivo;

public class DispositivoDto {
    private String id;
    private String nome;

    public DispositivoDto(Dispositivo dispositivo) {
        this.id = dispositivo.getId();
        this.nome = dispositivo.getNome();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
