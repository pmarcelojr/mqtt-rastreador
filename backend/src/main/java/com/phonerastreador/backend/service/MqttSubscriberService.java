package com.phonerastreador.backend.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonerastreador.backend.config.MqttCliente;
import com.phonerastreador.backend.controller.dto.MqttLocationDto;
import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.model.UserDispositivo;
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

    @Autowired
    private UserService userService;

    @Autowired
    private DispositivoService dispositivoService;
    
    @Autowired
    private LocalizacaoRepository repository;


    private String topico = "owntracks/#";

    private int qos = 0;

    private String padraoDispositivoUser = "^owntracks\\/([a-zA-Z0-9_]+)\\/([a-zA-Z0-9_]+)$";

    private Pattern pattern;

    @Autowired
    public void pattern() {
        this.pattern = Pattern.compile(this.padraoDispositivoUser, Pattern.CASE_INSENSITIVE);
    }

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

    private Optional<UserDispositivo> validarTopicoLocalizacao(String topico) {
        Matcher matcher = this.pattern.matcher(topico);
        if (matcher.find()) {
            return Optional.of(UserDispositivo.ofMatcher(matcher));
        }
        return Optional.empty();
    }

    private void salvarLocalizacao(String texto, UserDispositivo userDispositivo) throws JsonMappingException, JsonProcessingException {
        String username = userDispositivo.getUsername();
        String dispositivoNome = userDispositivo.getDispositivo();

        User usuario = this.userService.getByUsername(username);
        Dispositivo dispositivo = this.dispositivoService.getByNome(dispositivoNome);

        MqttLocationDto dto = this.mapper.readValue(texto, MqttLocationDto.class);
        Localizacao localizacao = dto.toLocalizacao(topico);
        localizacao.setUsername(usuario);
        localizacao.setDispositivo(dispositivo);

        Localizacao localizacaoSalva = this.repository.save(localizacao);

        log.debug("Localizacao salva: {} -> '{}'", topico, localizacaoSalva);
    }

    @Override
    public void messageArrived(String topico, MqttMessage mensagem) throws Exception {
        String texto = new String(mensagem.getPayload());
        log.info("texto recebido: '{}': {}", topico, texto);

        Optional<UserDispositivo> userLocalizacao = this.validarTopicoLocalizacao(topico);

        if (userLocalizacao.isPresent()) {
            UserDispositivo userDispositivo = userLocalizacao.get();
            try {
                this.salvarLocalizacao(texto, userDispositivo);   
            } catch (Exception e) {
                log.error("Erro ao salvar localizacao. Topico: '{}', payload: '{}'", topico, texto, e);
            }
        }
    }

}
