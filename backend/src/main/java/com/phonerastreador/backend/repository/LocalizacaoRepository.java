package com.phonerastreador.backend.repository;

import com.phonerastreador.backend.model.Localizacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, String> {
    
}
