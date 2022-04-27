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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private PBKDF2Service senhaService;

    @Autowired
    private DispositivoRepository repository;

    @Autowired
    private AclsRepository aclsRepository;

    @Value("${app.radical}")
    private String radical;

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

        String nome = form.getNome();
        String senha = this.senhaService.gerarHash(form.getSenha());
        String topico = this.gerarTopico(username, nome);

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

        this.aclsRepository.save(acls);

        return salvo;
    }

    public List<DispositivoDto> getDispositivos(String username) {
        List<Dispositivo> dispositivos = this.repository.getByUsuarioUsername(username);
        return dispositivos.stream().map(DispositivoDto::new).collect(Collectors.toList());
    }
}
