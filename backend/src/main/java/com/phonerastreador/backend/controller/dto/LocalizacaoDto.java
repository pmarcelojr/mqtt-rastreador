package com.phonerastreador.backend.controller.dto;

import java.io.Serializable;

import com.phonerastreador.backend.model.Localizacao;

public class LocalizacaoDto implements Serializable {

    private String id;
    private Double latitude;
    private Double longitude;
    private String bateriaStatus;
    private Integer bateria;
    private String gatilho;
    private String tipoConexao;
    private String horarioGps;
    private Integer velocidade;
    private DispositivoSimpleDto dispositivo;
    private Float pressaoBarometrica;
    private String endereco;

    public LocalizacaoDto(Localizacao localizacao) {
        this.id = localizacao.getId();
        this.latitude = localizacao.getLatitude();
        this.longitude = localizacao.getLongitude();
        this.bateriaStatus = localizacao.getBateriaStatus().name();
        this.bateria = localizacao.getBateria();
        this.gatilho = localizacao.getGatilho().name();
        this.tipoConexao = localizacao.getConexao().name();
        this.horarioGps = localizacao.getHorarioGps().toString();
        this.dispositivo = new DispositivoSimpleDto(localizacao.getDispositivo());
        this.velocidade = localizacao.getVelocidade();
        this.pressaoBarometrica = localizacao.getPressaoBarometrica();
        this.endereco = localizacao.getEndereco();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getBateria() {
        return this.bateria;
    }

    public void setBateria(Integer bateria) {
        this.bateria = bateria;
    }

    public Integer getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(Integer velocidade) {
        this.velocidade = velocidade;
    }

    public String getBateriaStatus() {
        return this.bateriaStatus;
    }

    public void setBateriaStatus(String bateriaStatus) {
        this.bateriaStatus = bateriaStatus;
    }

    public String getGatilho() {
        return this.gatilho;
    }

    public void setGatilho(String gatilho) {
        this.gatilho = gatilho;
    }

    public String getTipoConexao() {
        return this.tipoConexao;
    }

    public void setTipoConexao(String tipoConexao) {
        this.tipoConexao = tipoConexao;
    }

    public String getHorarioGps() {
        return this.horarioGps;
    }

    public void setHorarioGps(String horarioGps) {
        this.horarioGps = horarioGps;
    }

    public DispositivoSimpleDto getDispositivo() {
        return this.dispositivo;
    }

    public void setDispositivo(DispositivoSimpleDto dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Float getPressaoBarometrica() {
        return this.pressaoBarometrica;
    }

    public void setPressaoBarometrica(Float pressaoBarometrica) {
        this.pressaoBarometrica = pressaoBarometrica;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
