package com.phonerastreador.backend.controller.dto;

import java.time.LocalDateTime;

import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.model.Localizacao;

public class DispositivoDto {
    private String id;
    private String nome;
    private String topico;
    private String criadoEm;
    private String ultimaLocalizacao;
    private String ultimaAtualizacao;

    public DispositivoDto(Dispositivo dispositivo) {
        this.id = dispositivo.getId();
        this.nome = dispositivo.getNome();
        this.topico = dispositivo.getTopico();
        this.criadoEm = dispositivo.getCriadoEm().toString();
        this.setUltimaLocalizacao(dispositivo.getUltimaLocalizacao());
        this.setUltimaAtualizacao(dispositivo.getUltimaAtualizacao());
    }

    private void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        if (ultimaAtualizacao != null) {
            this.ultimaAtualizacao = ultimaAtualizacao.toString();
        }
    }

    private void setUltimaLocalizacao(Localizacao ultimaLocalizacao) {
        if (ultimaLocalizacao != null) {
            this.ultimaLocalizacao = ultimaLocalizacao.getEndereco();
        }
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

    public String getTopico() {
        return this.topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public String getCriadoEm() {
        return this.criadoEm;
    }

    public void setCriadoEm(String criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getUltimaLocalizacao() {
        return this.ultimaLocalizacao;
    }

    public void setUltimaLocalizacao(String ultimaLocalizacao) {
        this.ultimaLocalizacao = ultimaLocalizacao;
    }

    public String getUltimaAtualizacao() {
        return this.ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

}
