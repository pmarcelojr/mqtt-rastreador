package com.backend.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final Logger log = LoggerFactory.getLogger(MqttConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "mqtt")
    public MqttConnectOptions mqttConnectOptions() {
        return new MqttConnectOptions();
    }

    @Bean
    public IMqttClient mqttClient(@Value("${mqtt.clientId}") String clientId, @Value("${mqtt.hostname}") String hostname, @Value("${mqtt.port}") int port) throws MqttException {
        final String connectionString = String.format("tcp://%s:%d", hostname, port);
        log.info("Conectando MQTT Broker: '{}'. clientId: '{}'", connectionString, clientId);
        IMqttClient client = new MqttClient(connectionString, clientId, new MqttDefaultFilePersistence("./persistence"));
        client.connect(mqttConnectOptions());

        return client;
    }
}
