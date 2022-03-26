package com.phonerastreador.backend.config;

import javax.annotation.PostConstruct;

import com.phonerastreador.backend.service.MqttSubscriberService;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttInit {
    
    @PostConstruct
    public void inicializar() throws InterruptedException {
        MqttCliente cliente = new MqttCliente("tcp://localhost:1883", "backend_user", "str0ngp@ssword$mqtt");
        cliente.iniciar();

        new MqttSubscriberService(cliente, "owntracks/#", 0);
    }
}
