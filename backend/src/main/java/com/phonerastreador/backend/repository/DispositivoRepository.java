package com.phonerastreador.backend.repository;

import java.util.List;
import java.util.Optional;

import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, String> {

    Optional<Dispositivo> findByNome(String nomeDispositivo);

    List<Dispositivo> getByUsuarioUsername(String username);

    Optional<Dispositivo> findByUsuarioAndNome(User user, String nome);
}
