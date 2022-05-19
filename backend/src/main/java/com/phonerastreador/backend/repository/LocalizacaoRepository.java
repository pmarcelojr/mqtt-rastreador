package com.phonerastreador.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.phonerastreador.backend.model.Localizacao;
import com.phonerastreador.backend.model.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, String> {

    List<Localizacao> findByUsernameOrderByCriadoEmDesc(User usuario);

    List<Localizacao> findByUsernameOrderByCriadoEmDesc(User usuario, Pageable pagina);

    List<Localizacao> findByUsernameAndHorarioGpsBetween(User usuario, LocalDateTime de, LocalDateTime para);
    
}
