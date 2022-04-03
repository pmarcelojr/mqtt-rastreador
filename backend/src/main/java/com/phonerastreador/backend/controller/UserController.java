package com.phonerastreador.backend.controller;

import java.net.URI;
import java.util.Optional;

import com.phonerastreador.backend.controller.dto.UserDto;
import com.phonerastreador.backend.controller.form.NovaSenhaForm;
import com.phonerastreador.backend.controller.form.UserForm;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.repository.UserRepository;
import com.phonerastreador.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService service;

    @Autowired 
    private UserRepository repository;

    @PostMapping 
    public ResponseEntity<UserDto> criarUsuario(@RequestBody UserForm form, UriComponentsBuilder uriBuilder) {
        User usuario = this.service.criar(form);

        URI uri = uriBuilder.path("/user/{username}").buildAndExpand(usuario.getUsername()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(usuario));
    }

    @PatchMapping
    public ResponseEntity<Void> mudarSenha(Authentication auth, @RequestBody NovaSenhaForm form) {
        User user = (User) auth.getPrincipal();

        this.service.atualizarSenha(user, form.getSenha());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<UserDto> getUsuarioConectado(Authentication auth) {
        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(new UserDto(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUsuario(@PathVariable String username) {
        Optional<User> encontrado = this.repository.findByUsername(username);

        if (encontrado.isPresent()) {
            return ResponseEntity.ok(new UserDto(encontrado.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
