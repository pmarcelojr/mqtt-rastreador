package com.phonerastreador.backend.repository;

import java.util.List;

import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, String> {

    List<Localizacao> findByUsername(User usuario);
    
}
