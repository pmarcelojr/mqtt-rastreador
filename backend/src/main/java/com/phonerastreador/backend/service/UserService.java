package com.phonerastreador.backend.service;

import java.util.Optional;

import com.phonerastreador.backend.controller.form.UserForm;
import com.phonerastreador.backend.exception.EmailDuplicadoException;
import com.phonerastreador.backend.exception.UsernameDuplicadoException;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    @Qualifier(value = "BCrypt")
    private HashGeradorService hashService;

    public User criar(UserForm form) {
        User usuario = new User(form);

        log.info("Criando usuario: {}", usuario);

        Optional<User> existe = this.repository.findByUsernameOrEmail(usuario.getUsername(), usuario.getEmail());

        if (existe.isPresent()) {
            User duplicado = existe.get();
            if (duplicado.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new EmailDuplicadoException(usuario.getEmail());
            } else {
                throw new UsernameDuplicadoException(usuario.getUsername());
            }
        }

        log.info("Senha: {}", form.getSenha());

        usuario.setPassword(this.hashService.gerarHash(form.getSenha()));
        log.info("Hash: {}", form.getSenha(), usuario.getPassword());

        return this.repository.save(usuario);
    }

    public void atualizarSenha(User user, String senha) {
        Optional<User> encontrado = this.repository.findByUsername(user.getUsername());

        if (!encontrado.isPresent()) {
            throw new UsernameNotFoundException(user.getUsername());
        }

        User usuario = encontrado.get();
        usuario.setPassword(this.hashService.gerarHash(senha));

        this.repository.save(usuario);
    }

    public User getByUsername(String username) {

        Optional<User> encontrado = this.repository.findByUsername(username);
        if (encontrado.isPresent()) {
            return encontrado.get();
        }

        throw new UsernameNotFoundException(username);
    }
}
