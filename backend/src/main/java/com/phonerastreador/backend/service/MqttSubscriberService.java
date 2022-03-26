package com.phonerastreador.backend.service;

import com.phonerastreador.backend.config.MqttCliente;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttSubscriberService implements IMqttMessageListener {

    private static final Logger log = LoggerFactory.getLogger(MqttSubscriberService.class);

    public MqttSubscriberService(MqttCliente cliente, String topico, int qos) {
        log.info("Realizando inscricao em {}", topico);
        cliente.subscribe(qos, this, topico);
    }

    @Override
    public void messageArrived(String topico, MqttMessage mensagem) throws Exception {
        String texto = new String(mensagem.getPayload());
        log.info("Mensagem recebida: {} -> {}", topico, texto);
    }
    
}
