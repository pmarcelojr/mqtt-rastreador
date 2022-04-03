package com.phonerastreador.backend.service;

import java.util.Optional;

import com.phonerastreador.backend.controller.form.UserForm;
import com.phonerastreador.backend.exception.EmailDuplicadoException;
import com.phonerastreador.backend.exception.UsernameDuplicadoException;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    @Qualifier(value = "BCrypt")
    private HashGeradorService hashService;

    public User criar(UserForm form) {
        User usuario = new User(form);

        Optional<User> existe = this.repository.findByUsernameOrEmail(usuario.getUsername(), usuario.getEmail());

        if (existe.isPresent()) {
            User duplicado = existe.get();
            if (duplicado.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new EmailDuplicadoException(usuario.getEmail());
            } else {
                throw new UsernameDuplicadoException(usuario.getUsername());
            }
        }

        usuario.setPassword(this.hashService.gerarHash(form.getSenha()));
        User usuarioSalvo = this.repository.save(usuario);

        return usuarioSalvo;
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
}
