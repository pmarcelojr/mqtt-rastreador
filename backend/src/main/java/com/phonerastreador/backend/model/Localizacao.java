package com.phonerastreador.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Localizacao {
    @Id
    private String id;

    // precisao do GPS, em metros
    @Column
    private Integer precisao;

    // altitude em relação ao nível do mar, em metros
    @Column
    private Integer altitude;

    // precisão vertical da altitude
    @Column
    private Integer altitudePrecisao;

    // nível da bateria, em porcentagem
    @Column
    private Integer bateria;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private BateriaStatus bateriaStatus;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private MqttGatilho gatilho;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private TipoConexao conexao;

    @Column(nullable = false)
    private LocalDateTime horarioGps;

    @Column(nullable = true)
    private LocalDateTime horarioDispositivo;

    @Column(nullable = true)
    private String ssid;

    @Column(nullable = true)
    private String bssid;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = true)
    private Integer velocidade;

    @Column(nullable = true)
    private Float pressaoBarometrica;

    @Column(nullable = false)
    private String topico;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private ModoMonitoramento modoMonitor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User username;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id", nullable = false, updatable = false)
    private Dispositivo dispositivo;

    @Column
    private String endereco;

    public Localizacao() {
        // construtor vazio
    }

    @PrePersist
    public void gerarId() {
        this.id = UUID.randomUUID().toString();
        this.criadoEm = LocalDateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getAltitudePrecisao() {
        return this.altitudePrecisao;
    }

    public void setAltitudePrecisao(Integer altitudePrecisao) {
        this.altitudePrecisao = altitudePrecisao;
    }

    public Integer getBateria() {
        return this.bateria;
    }

    public void setBateria(Integer bateria) {
        this.bateria = bateria;
    }

    public BateriaStatus getBateriaStatus() {
        return this.bateriaStatus;
    }

    public void setBateriaStatus(BateriaStatus bateriaStatus) {
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

    public MqttGatilho getGatilho() {
        return this.gatilho;
    }

    public void setGatilho(MqttGatilho gatilho) {
        this.gatilho = gatilho;
    }

    public TipoConexao getConexao() {
        return this.conexao;
    }

    public void setConexao(TipoConexao conexao) {
        this.conexao = conexao;
    }

    public LocalDateTime getHorarioGps() {
        return this.horarioGps;
    }

    public void setHorarioGps(LocalDateTime horarioGps) {
        this.horarioGps = horarioGps;
    }

    public LocalDateTime getHorarioDispositivo() {
        return this.horarioDispositivo;
    }

    public void setHorarioDispositivo(LocalDateTime horarioDispositivo) {
        this.horarioDispositivo = horarioDispositivo;
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

    public LocalDateTime getCriadoEm() {
        return this.criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Integer getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(Integer velocidade) {
        this.velocidade = velocidade;
    }

    public Float getPressaoBarometrica() {
        return this.pressaoBarometrica;
    }

    public void setPressaoBarometrica(Float pressaoBarometrica) {
        this.pressaoBarometrica = pressaoBarometrica;
    }

    public String getTopico() {
        return this.topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public ModoMonitoramento getModoMonitor() {
        return this.modoMonitor;
    }

    public void setModoMonitor(ModoMonitoramento modoMonitor) {
        this.modoMonitor = modoMonitor;
    }

    public User getUsername() {
        return this.username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Dispositivo getDispositivo() {
        return this.dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", precisao='" + getPrecisao() + "'" +
                ", altitude='" + getAltitude() + "'" +
                ", altitudePrecisao='" + getAltitudePrecisao() + "'" +
                ", bateria='" + getBateria() + "'" +
                ", bateriaStatus='" + getBateriaStatus() + "'" +
                ", latitude='" + getLatitude() + "'" +
                ", longitude='" + getLongitude() + "'" +
                ", gatilho='" + getGatilho() + "'" +
                ", conexao='" + getConexao() + "'" +
                ", horarioGps='" + getHorarioGps() + "'" +
                ", horarioDispositivo='" + getHorarioDispositivo() + "'" +
                ", ssid='" + getSsid() + "'" +
                ", bssid='" + getBssid() + "'" +
                ", criadoEm='" + getCriadoEm() + "'" +
                ", velocidade='" + getVelocidade() + "'" +
                ", pressaoBarometrica='" + getPressaoBarometrica() + "'" +
                ", topico='" + getTopico() + "'" +
                ", modoMonitor='" + getModoMonitor() + "'" +
                "}";
    }

}
