package com.phonerastreador.backend.service;

import java.util.Optional;

import com.phonerastreador.backend.exception.DispositivoNaoEncontradoException;
import com.phonerastreador.backend.model.Dispositivo;
import com.phonerastreador.backend.model.OwnTracksConfig;
import com.phonerastreador.backend.model.User;
import com.phonerastreador.backend.repository.DispositivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportarService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public OwnTracksConfig exportarConfiguracao(User user, String nome) {
        Optional<Dispositivo> encontrado = this.dispositivoRepository.findByUsuarioAndNome(user, nome);

        if (!encontrado.isPresent()) {
            throw new DispositivoNaoEncontradoException(nome);
        }

        Dispositivo dispositivo = encontrado.get();
        return new OwnTracksConfig(dispositivo);
    }
    
}
