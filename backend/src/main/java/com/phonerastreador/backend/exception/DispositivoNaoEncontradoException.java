package com.phonerastreador.backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DispositivoNaoEncontradoException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(DispositivoNaoEncontradoException.class);
    
    public static String gerarMensagem(String nomeDispositivo) {
        String mensagem = String.format("Dispositivo nao encontrado: '%s'", nomeDispositivo);
        log.error(mensagem);
        return mensagem;
    }

    public DispositivoNaoEncontradoException(String nomeDispositivo) {
        super(DispositivoNaoEncontradoException.gerarMensagem(nomeDispositivo));
    }
}
