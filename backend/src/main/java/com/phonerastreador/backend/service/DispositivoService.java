package com.phonerastreador.backend.service;

import java.util.Optional;

import com.phonerastreador.backend.controller.form.DispositivoForm;
import com.phonerastreador.backend.exception.DispositivoNaoEncontradoException;
import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.repository.DispositivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    
    @Autowired
    private DispositivoRepository repository;

    @Autowired
    private UserService userService;

    public Dispositivo getByNome(String nome) {
        Optional<Dispositivo> encontrado = this.repository.findByNome(nome);

        if (encontrado.isPresent()) {
            return encontrado.get();
        }

        throw new DispositivoNaoEncontradoException(nome);
    }

    public Dispositivo salvar(DispositivoForm form) {
        String username = form.getUsuario();
        String nome = form.getNome();

        User usuario = this.userService.getByUsername(username);

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setUsuario(usuario);
        dispositivo.setNome(nome);

        return this.repository.save(dispositivo);
    }
}
