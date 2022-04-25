package com.phonerastreador.backend.service;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonerastreador.backend.config.MqttCliente;
import com.phonerastreador.backend.controller.dto.MqttLocationDto;
import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.repository.LocalizacaoRepository;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttSubscriberService implements IMqttMessageListener {

    private static final Logger log = LoggerFactory.getLogger(MqttSubscriberService.class);

    @Autowired
    private ObjectMapper mapper;

    private String topico = "owntracks/#";

    private int qos = 0;

    @Autowired
    private LocalizacaoRepository repository;

    @Autowired
    private MqttCliente cliente;

    public MqttSubscriberService() {
        // construtor vazio
    }

    @PostConstruct
    public void realizarInscricao() {
        log.info("Realizando inscricao em {}", topico);
        cliente.subscribe(qos, this, topico);
    }

    @Override
    public void messageArrived(String topico, MqttMessage mensagem) throws Exception {
        String texto = new String(mensagem.getPayload());
        log.info("texto recebido: '{}': {}", topico, texto);
        try {
            MqttLocationDto dto = this.mapper.readValue(texto, MqttLocationDto.class);
            Localizacao localizacao = dto.toLocalizacao(topico);

            Localizacao localizacaoSalva = this.repository.save(localizacao);
    
            log.debug("Localizacao salva: {} -> '{}'", topico, localizacaoSalva);
        } catch (JsonParseException e) {
            log.error("Erro ao deserializar JSON '{}':", texto);
        }
    }

}
