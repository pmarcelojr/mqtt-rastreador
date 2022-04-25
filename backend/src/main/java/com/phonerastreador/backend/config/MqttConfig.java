package com.phonerastreador.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private String serverUri = "tcp://localhost:1883";

    private String usuario = "backend_user";

    private String senha = "str0ngp@ssword$mqtt";

    @Bean
    public MqttCliente clienteInicializado() {
        MqttCliente cliente = new MqttCliente(this.serverUri, this.usuario, this.senha);
        cliente.iniciar();
        return cliente;
    }

}
