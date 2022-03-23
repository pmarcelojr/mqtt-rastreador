package com.backend.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Value("${backend.mqtt.username}")
    private String username;

    @Value("${backend.mqtt.password}")
    private String password;

    @Bean
    @ConfigurationProperties(prefix = "mqtt")
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(this.username);
        options.setPassword(this.password.toCharArray());
        return options;
    }

    @Bean
    public IMqttClient mqttClient(@Value("${mqtt.clientId}") String clientId, @Value("${mqtt.hostname}") String hostname, @Value("${mqtt.port}") int port) throws MqttException {
        IMqttClient client = new MqttClient(String.format("tcp://%s:%d", hostname, port), clientId, new MqttDefaultFilePersistence("./persistence"));
        client.connect(mqttConnectOptions());

        return client;
    }
}
