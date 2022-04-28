package com.phonerastreador.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final Logger log = LoggerFactory.getLogger(MqttConfig.class);

    @Value("${app.mqtt.servidor}")
    private String servidor;

    @Value("${app.mqtt.porta}")
    private String porta;

    @Value("${app.mqtt.username}")
    private String usuario;

    @Value("${app.mqtt.senha}")
    private String senha;

    @Bean
    public MqttCliente cliente() {
        String serverUri = String.format("tcp://%s:%s", this.servidor, this.porta);
        log.info("Realizando conexao a servidor MQTT.\nHost: {}\nUsername: {}", serverUri, this.usuario);
        MqttCliente cliente = new MqttCliente(serverUri, this.usuario, this.senha);
        return cliente;
    }

}
