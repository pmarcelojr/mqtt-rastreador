package com.phonerastreador.backend.controller;

import java.net.URI;
import java.security.Principal;

import com.phonerastreador.backend.controller.dto.DispositivoDto;
import com.phonerastreador.backend.controller.form.DispositivoForm;
import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.service.DispositivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {

    @Autowired
    private DispositivoService service;

    @PostMapping
    public ResponseEntity<DispositivoDto> addDispositivo(@RequestBody DispositivoForm form,
            UriComponentsBuilder uriBuilder, Principal loggedUser) {

        form.setUsuario(loggedUser.getName());
        Dispositivo dispositivo = this.service.salvar(form);

        URI uri = uriBuilder.path("/dispositivo/{id}").buildAndExpand(dispositivo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DispositivoDto(dispositivo));
    }
}
