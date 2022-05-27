package com.phonerastreador.backend.controller;

import com.phonerastreador.backend.model.OwnTracksConfig;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.service.ExportarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exportar")
public class ExportarController {

    @Autowired
    private ExportarService service;

    @GetMapping("/config/{nome}")
    public ResponseEntity<OwnTracksConfig> gerarArquivo(@PathVariable String nome, Authentication usuarioConectado) {
        User user = (User) usuarioConectado.getPrincipal();

        OwnTracksConfig arquivo = this.service.exportarConfiguracao(user, nome);
        return ResponseEntity.ok(arquivo);
    }

}
