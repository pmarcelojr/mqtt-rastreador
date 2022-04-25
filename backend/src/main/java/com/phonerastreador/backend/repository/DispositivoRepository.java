package com.phonerastreador.backend.repository;

import java.util.Optional;

import com.phonerastreador.backend.model.Dispositivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, String> {

    Optional<Dispositivo> findByNome(String nomeDispositivo);
}
