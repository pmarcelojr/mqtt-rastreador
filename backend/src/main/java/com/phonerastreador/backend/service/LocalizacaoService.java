package com.phonerastreador.backend.service;

import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.repository.LocalizacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repository;

    public void salvar(Localizacao localizacao) {
        this.repository.save(localizacao);
    }
}
