package com.phonerastreador.backend.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.phonerastreador.backend.controller.dto.LocalizacaoDto;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.service.LocalizacaoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoController {

    private static final Logger log = LoggerFactory.getLogger(LocalizacaoController.class);

    @Autowired
    private LocalizacaoService service;

    @GetMapping
    public Page<LocalizacaoDto> getLocalizacoes(
            Authentication userConectado,
            @PageableDefault(size = 50) Pageable pagina,
            @RequestParam(required = false) String de,
            @RequestParam(required = false) String para) {

        User usuario = (User) userConectado.getPrincipal();
        LocalDate dataInicio = LocalDate.now().minusDays(1);
        LocalDate dataFim = LocalDate.now();

        if (de != null && para != null) {
            dataInicio = LocalDate.parse(de, DateTimeFormatter.ISO_LOCAL_DATE);
            dataFim = LocalDate.parse(para, DateTimeFormatter.ISO_LOCAL_DATE);
            log.info("Datas recebidas: {} e {}", de, para);
        }

        if (dataInicio.isAfter(dataFim)) {
            LocalDate dataTemp = dataFim;
            dataFim = dataInicio;
            dataInicio = dataTemp;
        }

        return this.service.getDePara(usuario, dataInicio, dataFim, pagina).map(LocalizacaoDto::new);
    }

    @GetMapping("/recentes")
    public ResponseEntity<List<LocalizacaoDto>> getRecentes(Authentication userConectado,
            @RequestParam(required = false, defaultValue = "20") Integer quantidade) {
        User usuario = (User) userConectado.getPrincipal();
        return ResponseEntity.ok(this.service.getByUser(usuario, quantidade).stream()
                .map(LocalizacaoDto::new).collect(Collectors.toList()));
    }
}
