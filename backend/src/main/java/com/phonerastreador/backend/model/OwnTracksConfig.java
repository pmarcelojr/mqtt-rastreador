package com.phonerastreador.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Value;

public class OwnTracksConfig {

    @Value("${app.config.host}")
    private String host = "phonerastreador.ml";

    private Integer locatorDisplacement = 200;
    private String subTopic = "";
    private Integer ignoreStaleLocations = 0;
    private Boolean cleanSession = false;
    private Boolean locked = false;
    private String url = "";
    private Boolean usePassword = true;
    private Integer positions = 50;
    private Integer mode = 0;
    private Boolean tls = false;
    private Boolean willRetain = false;
    private Integer downgrade = 0;
    private Integer ignoreInaccurateLocations = 0;
    private String deviceId = "";
    private Boolean extendedData = true;
    private Integer monitoring = 2;

    @JsonProperty("_type")
    private String type = "configuration";

    private Integer locatorInterval = 180;
    private Integer maxHistory = 0;
    private String[] waypoints = new String[0];

    private Boolean ws = false;
    private String username; // device username
    private String tid = "";
    private String willTopic = "";
    private String clientpkcs = "";
    private Boolean ranging = false;
    private Integer keepalive = 60;
    private Integer willQos = 1;
    private String pubTopicBase; // escapar barras - ex: owntracks\/marcelo\/iphone"
    private Boolean auth = true;
    private String passphrase = "";
    private Boolean allowinvalidcerts = true;
    private Integer port = 1883;
    private String clientId; // usar id do dispositivo
    private Integer mqttProtocolLevel = 4;
    private Boolean sub = false;
    private Boolean allowRemoteLocation = true;
    private Integer pubQos = 1;
    private String password; // senha do dispositivo
    private Integer subQos = 1;
    private Boolean pubRetain = false;
    private Boolean cmd = false;

    public OwnTracksConfig(String pubTopicBase, String dispositivoUsername, String dispositivoId) {
        this.pubTopicBase = pubTopicBase;
        this.username = dispositivoUsername;
        this.clientId = dispositivoId;
    }

    public OwnTracksConfig(Dispositivo dispositivo) {
        this.pubTopicBase = dispositivo.getTopico();
        this.username = dispositivo.getNome();
        this.clientId = dispositivo.getId();
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getLocatorDisplacement() {
        return this.locatorDisplacement;
    }

    public void setLocatorDisplacement(Integer locatorDisplacement) {
        this.locatorDisplacement = locatorDisplacement;
    }

    public String getSubTopic() {
        return this.subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public Integer getIgnoreStaleLocations() {
        return this.ignoreStaleLocations;
    }

    public void setIgnoreStaleLocations(Integer ignoreStaleLocations) {
        this.ignoreStaleLocations = ignoreStaleLocations;
    }

    public Boolean isCleanSession() {
        return this.cleanSession;
    }

    public Boolean getCleanSession() {
        return this.cleanSession;
    }

    public void setCleanSession(Boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public Boolean isLocked() {
        return this.locked;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isUsePassword() {
        return this.usePassword;
    }

    public Boolean getUsePassword() {
        return this.usePassword;
    }

    public void setUsePassword(Boolean usePassword) {
        this.usePassword = usePassword;
    }

    public Integer getPositions() {
        return this.positions;
    }

    public void setPositions(Integer positions) {
        this.positions = positions;
    }

    public Integer getMode() {
        return this.mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Boolean isTls() {
        return this.tls;
    }

    public Boolean getTls() {
        return this.tls;
    }

    public void setTls(Boolean tls) {
        this.tls = tls;
    }

    public Boolean isWillRetain() {
        return this.willRetain;
    }

    public Boolean getWillRetain() {
        return this.willRetain;
    }

    public void setWillRetain(Boolean willRetain) {
        this.willRetain = willRetain;
    }

    public Integer getDowngrade() {
        return this.downgrade;
    }

    public void setDowngrade(Integer downgrade) {
        this.downgrade = downgrade;
    }

    public Integer getIgnoreInaccurateLocations() {
        return this.ignoreInaccurateLocations;
    }

    public void setIgnoreInaccurateLocations(Integer ignoreInaccurateLocations) {
        this.ignoreInaccurateLocations = ignoreInaccurateLocations;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean isExtendedData() {
        return this.extendedData;
    }

    public Boolean getExtendedData() {
        return this.extendedData;
    }

    public void setExtendedData(Boolean extendedData) {
        this.extendedData = extendedData;
    }

    public Integer getMonitoring() {
        return this.monitoring;
    }

    public void setMonitoring(Integer monitoring) {
        this.monitoring = monitoring;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLocatorInterval() {
        return this.locatorInterval;
    }

    public void setLocatorInterval(Integer locatorInterval) {
        this.locatorInterval = locatorInterval;
    }

    public Integer getMaxHistory() {
        return this.maxHistory;
    }

    public void setMaxHistory(Integer maxHistory) {
        this.maxHistory = maxHistory;
    }

    public String[] getWaypoints() {
        return this.waypoints;
    }

    public void setWaypoints(String[] waypoints) {
        this.waypoints = waypoints;
    }

    public Boolean isWs() {
        return this.ws;
    }

    public Boolean getWs() {
        return this.ws;
    }

    public void setWs(Boolean ws) {
        this.ws = ws;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getWillTopic() {
        return this.willTopic;
    }

    public void setWillTopic(String willTopic) {
        this.willTopic = willTopic;
    }

    public String getClientpkcs() {
        return this.clientpkcs;
    }

    public void setClientpkcs(String clientpkcs) {
        this.clientpkcs = clientpkcs;
    }

    public Boolean isRanging() {
        return this.ranging;
    }

    public Boolean getRanging() {
        return this.ranging;
    }

    public void setRanging(Boolean ranging) {
        this.ranging = ranging;
    }

    public Integer getKeepalive() {
        return this.keepalive;
    }

    public void setKeepalive(Integer keepalive) {
        this.keepalive = keepalive;
    }

    public Integer getWillQos() {
        return this.willQos;
    }

    public void setWillQos(Integer willQos) {
        this.willQos = willQos;
    }

    public String getPubTopicBase() {
        return this.pubTopicBase;
    }

    public void setPubTopicBase(String pubTopicBase) {
        this.pubTopicBase = pubTopicBase;
    }

    public Boolean isAuth() {
        return this.auth;
    }

    public Boolean getAuth() {
        return this.auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public String getPassphrase() {
        return this.passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public Boolean isAllowinvalidcerts() {
        return this.allowinvalidcerts;
    }

    public Boolean getAllowinvalidcerts() {
        return this.allowinvalidcerts;
    }

    public void setAllowinvalidcerts(Boolean allowinvalidcerts) {
        this.allowinvalidcerts = allowinvalidcerts;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getMqttProtocolLevel() {
        return this.mqttProtocolLevel;
    }

    public void setMqttProtocolLevel(Integer mqttProtocolLevel) {
        this.mqttProtocolLevel = mqttProtocolLevel;
    }

    public Boolean isSub() {
        return this.sub;
    }

    public Boolean getSub() {
        return this.sub;
    }

    public void setSub(Boolean sub) {
        this.sub = sub;
    }

    public Boolean isAllowRemoteLocation() {
        return this.allowRemoteLocation;
    }

    public Boolean getAllowRemoteLocation() {
        return this.allowRemoteLocation;
    }

    public void setAllowRemoteLocation(Boolean allowRemoteLocation) {
        this.allowRemoteLocation = allowRemoteLocation;
    }

    public Integer getPubQos() {
        return this.pubQos;
    }

    public void setPubQos(Integer pubQos) {
        this.pubQos = pubQos;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSubQos() {
        return this.subQos;
    }

    public void setSubQos(Integer subQos) {
        this.subQos = subQos;
    }

    public Boolean isPubRetain() {
        return this.pubRetain;
    }

    public Boolean getPubRetain() {
        return this.pubRetain;
    }

    public void setPubRetain(Boolean pubRetain) {
        this.pubRetain = pubRetain;
    }

    public Boolean isCmd() {
        return this.cmd;
    }

    public Boolean getCmd() {
        return this.cmd;
    }

    public void setCmd(Boolean cmd) {
        this.cmd = cmd;
    }
}
