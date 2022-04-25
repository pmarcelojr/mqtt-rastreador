package com.phonerastreador.backend.controller.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.phonerastreador.backend.model.BateriaStatus;
import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.model.ModoMonitoramento;
import com.phonerastreador.backend.model.MqttGatilho;
import com.phonerastreador.backend.model.TipoConexao;

/**
 * MqttLocationDto
 * Referência: https://owntracks.org/booklet/tech/json/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MqttLocationDto implements Serializable {

    // _type - tipo de mensagem enviada
    @JsonProperty("_type")
    private String tipo;

    // acc - precisão do GPS em metros (accuracy)
    @JsonProperty("acc")
    private Integer precisao;

    // alt - altitude acima do nível do mar, em metros
    @JsonProperty("alt")
    private Integer altitude;

    // vac - Precisão de altitude, em metros
    @JsonProperty("vac")
    private Integer vac;

    // batt - nível da bateria, em porcentagem
    @JsonProperty("batt")
    private Integer bateria;

    // bs - status da bateria (0=unknown, 1=unplugged, 2=charging, 3=full (iOS,
    // Android))
    @JsonProperty("bs")
    private Integer bateriaStatus;

    // lat - Latitude
    @JsonProperty("lat")
    private Double latitude;

    // lon - Longitude
    @JsonProperty("lon")
    private Double longitude;

    /**
     * t - Gatilho (trigger) para o reporte de localização
     * (iOS,Android/string/optional)
     * p: ping issued randomly by background task (iOS,Android)
     * c: circular region enter/leave event (iOS,Android)
     * b: beacon region enter/leave event (iOS)
     * r: response to a reportLocation cmd message (iOS,Android)
     * u: manual publish requested by the user (iOS,Android)
     * t: timer based publish in move move (iOS)
     * v: updated by Settings/Privacy/Locations Services/System Services/Frequent
     * Locations monitoring (iOS)
     */
    @JsonProperty("t")
    private char gatilho;

    // conn - Tipo de conexão (w = wi-fi, o = offline, m = dados móveis)
    @JsonProperty("conn")
    private char conexao;

    // p - Pressão Barométrica (em kPa - quilopascal)
    @JsonProperty("p")
    private Float pressaoBarometrica;

    // vel - Velocidade em KM/h
    @JsonProperty("vel")
    private Integer velocidade;

    // m - identifica o modo de monitoramento que a mensagem foi construída
    // (significativo=1, movimento=2)
    @JsonProperty("m")
    private Integer modoMonitor;

    // tst - Horário segundo GPS
    @JsonProperty("tst")
    private Long horarioGps;

    // created_at - horário segundo celular
    @JsonProperty("created_at")
    private Long criadoEm;

    // SSID - Nome da rede Wireless, se disponível
    @JsonProperty("SSID")
    private String ssid;

    // BSSID - Endereço Mac do ponto de acesso, se disponível
    @JsonProperty("BSSID")
    private String bssid;

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPrecisao() {
        return this.precisao;
    }

    public void setPrecisao(Integer precisao) {
        this.precisao = precisao;
    }

    public Integer getAltitude() {
        return this.altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getVac() {
        return this.vac;
    }

    public void setVac(Integer vac) {
        this.vac = vac;
    }

    public Integer getBateria() {
        return this.bateria;
    }

    public void setBateria(Integer bateria) {
        this.bateria = bateria;
    }

    public Integer getBateriaStatus() {
        return this.bateriaStatus;
    }

    public void setBateriaStatus(Integer bateriaStatus) {
        this.bateriaStatus = bateriaStatus;
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

    public char getGatilho() {
        return this.gatilho;
    }

    public void setGatilho(char gatilho) {
        this.gatilho = gatilho;
    }

    public char getConexao() {
        return this.conexao;
    }

    public void setConexao(char conexao) {
        this.conexao = conexao;
    }

    public Float getPressaoBarometrica() {
        return this.pressaoBarometrica;
    }

    public void setPressaoBarometrica(Float pressaoBarometrica) {
        this.pressaoBarometrica = pressaoBarometrica;
    }

    public Integer getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(Integer velocidade) {
        this.velocidade = velocidade;
    }

    public Integer getModoMonitor() {
        return this.modoMonitor;
    }

    public void setModoMonitor(Integer modoMonitor) {
        this.modoMonitor = modoMonitor;
    }

    public Long getHorarioGps() {
        return this.horarioGps;
    }

    public void setHorarioGps(Long horarioGps) {
        this.horarioGps = horarioGps;
    }

    public Long getCriadoEm() {
        return this.criadoEm;
    }

    public void setCriadoEm(Long criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return this.bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    @Override
    public String toString() {
        return "{" +
                " tipo='" + getTipo() + "'" +
                ", precisao='" + getPrecisao() + "'" +
                ", altitude='" + getAltitude() + "'" +
                ", vac='" + getVac() + "'" +
                ", bateria='" + getBateria() + "'" +
                ", bateriaStatus='" + getBateriaStatus() + "'" +
                ", latitude='" + getLatitude() + "'" +
                ", longitude='" + getLongitude() + "'" +
                ", gatilho='" + getGatilho() + "'" +
                ", conexao='" + getConexao() + "'" +
                ", pressaoBarometrica='" + getPressaoBarometrica() + "'" +
                ", velocidade='" + getVelocidade() + "'" +
                ", modoMonitor='" + getModoMonitor() + "'" +
                ", horarioGps='" + getHorarioGps() + "'" +
                ", criadoEm='" + getCriadoEm() + "'" +
                ", ssid='" + getSsid() + "'" +
                ", bssid='" + getBssid() + "'" +
                "}";
    }

    public Localizacao toLocalizacao(String topico) {
        Localizacao localizacao = new Localizacao();
        localizacao.setTopico(topico);
        localizacao.setPrecisao(precisao);
        localizacao.setAltitude(altitude);
        localizacao.setAltitudePrecisao(vac);
        localizacao.setBateria(bateria);
        localizacao.setBateriaStatus(BateriaStatus.fromInteger(this.bateriaStatus));
        localizacao.setLatitude(latitude);
        localizacao.setLongitude(longitude);
        localizacao.setGatilho(MqttGatilho.fromChar(gatilho));
        localizacao.setConexao(TipoConexao.fromChar(conexao));
        localizacao.setPressaoBarometrica(pressaoBarometrica);
        localizacao.setVelocidade(velocidade);
        localizacao.setModoMonitor(ModoMonitoramento.fromInteger(modoMonitor));

        if (horarioGps != null) {
            localizacao.setHorarioGps(LocalDateTime.ofInstant(Instant.ofEpochMilli(horarioGps * 1000), ZoneId.of("Z")));
        }
        
        if (criadoEm != null) {
            localizacao.setHorarioDispositivo(LocalDateTime.ofInstant(Instant.ofEpochMilli(criadoEm * 1000), ZoneId.of("Z")));
        }

        localizacao.setSsid(ssid);
        localizacao.setBssid(bssid);
        return localizacao;
    }

}
