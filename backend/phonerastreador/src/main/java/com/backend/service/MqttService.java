package com.backend.service;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    @Autowired
    private IMqttClient client;

    private static final Logger log = LoggerFactory.getLogger(MqttService.class);

    public void subscribe(final String topic) throws MqttException, InterruptedException {
        log.info("Message received");

        client.subscribeWithResponse(topic, (tpic, msg) -> {
            log.info("{} -> {}", msg.getId(), new String(msg.getPayload()));
        });
    }

    public void publish(final String topic, final String payload, int qos, boolean retained) throws MqttPersistenceException, MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload(payload.getBytes());
        message.setQos(qos);
        message.setRetained(retained);

        this.client.publish(topic, message);
    }

    @Autowired
    public void run() {
        log.info("Starting MqttService");

        try {
            this.subscribe("owntracks/#");

            this.publish("owntracks/server", "Backend Server Started", 0, false);
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
