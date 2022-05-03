package com.phonerastreador.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.phonerastreador.backend.controller.dto.LocalizacaoDto;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.service.LocalizacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;

    @GetMapping
    public ResponseEntity<List<LocalizacaoDto>> getLocalizacoes(Authentication userConectado) {
        User usuario = (User) userConectado.getPrincipal();
        return ResponseEntity.ok(this.service.getByUser(usuario).stream()
        .map(LocalizacaoDto::new).collect(Collectors.toList()));
    }
}
