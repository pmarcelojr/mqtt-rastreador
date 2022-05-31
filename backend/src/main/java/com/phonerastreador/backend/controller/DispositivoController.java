package com.phonerastreador.backend.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import com.phonerastreador.backend.controller.dto.DispositivoDto;
import com.phonerastreador.backend.controller.form.DispositivoForm;
import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.service.DispositivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
            UriComponentsBuilder uriBuilder, Principal usuario) {

        Dispositivo dispositivo = this.service.salvar(form, usuario.getName());

        URI uri = uriBuilder.path("/dispositivo/{id}").buildAndExpand(dispositivo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DispositivoDto(dispositivo));
    }

    @GetMapping
    public ResponseEntity<List<DispositivoDto>> getDispositivos(Principal usuario) {

        List<DispositivoDto> lista = this.service.getDispositivos(usuario.getName());

        return ResponseEntity.ok(lista);
    }
}
