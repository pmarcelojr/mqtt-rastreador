package com.phonerastreador.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.phonerastreador.backend.controller.dto.DispositivoDto;
import com.phonerastreador.backend.controller.form.DispositivoForm;
import com.phonerastreador.backend.exception.DispositivoNaoEncontradoException;
import com.phonerastreador.backend.model.Acls;
import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.repository.AclsRepository;
import com.phonerastreador.backend.repository.DispositivoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    
    private static final Logger log = LoggerFactory.getLogger(DispositivoService.class);

    @Autowired
    private PBKDF2Service senhaService;

    @Autowired
    private DispositivoRepository repository;

    @Autowired
    private AclsRepository aclsRepository;

    @Value("${app.radical}")
    private String radical;

    @Value("${app.mqtt.username}")
    private String mqttUsername;

    @Value("${app.mqtt.senha}")
    private String mqttSenha;

    @Value("${app.mqtt.email}")
    private String mqttEmail;

    @Value("${app.mqtt.nome}")
    private String mqttNome;

    private String gerarTopico(String username, String nome) {
        return String.format("%s/%s/%s", this.radical, username, nome);
    }

    @Autowired
    private UserService userService;

    public Dispositivo getByNome(String nome) {
        Optional<Dispositivo> encontrado = this.repository.findByNome(nome);

        if (encontrado.isPresent()) {
            return encontrado.get();
        }

        throw new DispositivoNaoEncontradoException(nome);
    }

    public Dispositivo salvar(DispositivoForm form, String username) {
        User usuario = this.userService.getByUsername(username);

        final String nome = form.getNome();
        final String senha = this.senhaService.gerarHash(form.getSenha());
        final String topico = this.gerarTopico(username, nome);

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setUsuario(usuario);
        dispositivo.setNome(nome);
        dispositivo.setSenha(senha);
        dispositivo.setTopico(topico);

        Dispositivo salvo = this.repository.save(dispositivo);

        Acls acls = new Acls();
        acls.setRw(Acls.LER_GRAVAR);
        acls.setUsername(username);
        acls.setTopic(topico);
        acls.setNome(nome);

        this.aclsRepository.save(acls);

        return salvo;
    }

    public List<DispositivoDto> getDispositivos(String username) {
        List<Dispositivo> dispositivos = this.repository.getByUsuarioUsername(username);
        return dispositivos.stream().map(DispositivoDto::new).collect(Collectors.toList());
    }

    public boolean verificarBackendUserExiste() {
        Optional<Dispositivo> existe = this.repository.findByNome(this.mqttUsername);
        return existe.isPresent();
    }

    public Dispositivo criarUsuarioMqtt() {
        String topico = String.format("%s/#", this.radical);
        String senha = this.senhaService.gerarHash(this.mqttSenha);
        this.userService.deletarSuperUserSeExistir(this.mqttUsername);

        log.info("Criando super usuario '{}'", this.mqttUsername);
        User user = new User();
        user.setUsername(this.mqttUsername);
        user.setEmail(this.mqttEmail);
        user.setPassword(senha);
        user.setNome(this.mqttNome);
        User novoSuperUsuario = this.userService.criar(user);

        log.info("Criando Dispositivo para super usuario '{}'", this.mqttUsername);
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setAdmin(1);
        dispositivo.setNome(this.mqttUsername);
        dispositivo.setSenha(senha);
        dispositivo.setUsuario(novoSuperUsuario);
        dispositivo.setTopico(topico);

        log.info("Criando ACLS para super usuario '{}'. Topico: '{}'", this.mqttUsername, topico);
        Acls acls = new Acls();
        acls.setNome(this.mqttUsername);
        acls.setUsername(this.mqttUsername);
        acls.setRw(Acls.LER_GRAVAR);
        acls.setTopic(topico);
        this.aclsRepository.save(acls);

        return this.repository.save(dispositivo);
    }
}
