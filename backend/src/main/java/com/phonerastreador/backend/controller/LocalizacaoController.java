package com.phonerastreador.backend.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import com.phonerastreador.backend.controller.dto.LocalizacaoDto;
import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.service.LocalizacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;

    @GetMapping
    public ResponseEntity<List<LocalizacaoDto>> getLocalizacoes(
            Authentication userConectado,
            @RequestParam(required = false) Long from,
            @RequestParam(required = false) Long to) {

        User usuario = (User) userConectado.getPrincipal();
        if (from != null && to != null) {
            LocalDateTime dateFrom = LocalDateTime.ofInstant(Instant.ofEpochMilli(from), ZoneId.of("Z"));
            LocalDateTime dateTo = LocalDateTime.ofInstant(Instant.ofEpochMilli(to), ZoneId.of("Z"));

            List<Localizacao> localizacoes = this.service.getDePara(usuario, dateFrom, dateTo);
            return ResponseEntity.ok(localizacoes.stream()
                    .map(LocalizacaoDto::new).collect(Collectors.toList()));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/recentes")
    public ResponseEntity<List<LocalizacaoDto>> getRecentes(Authentication userConectado,
            @RequestParam(required = false, defaultValue = "20") Integer quantidade) {
        User usuario = (User) userConectado.getPrincipal();
        return ResponseEntity.ok(this.service.getByUser(usuario, quantidade).stream()
                .map(LocalizacaoDto::new).collect(Collectors.toList()));
    }
}
