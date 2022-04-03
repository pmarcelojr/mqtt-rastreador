package com.phonerastreador.backend.exception;

public class EmailDuplicadoException extends RuntimeException {

    public EmailDuplicadoException(String email) {
        super(String.format("Email já está em uso: '%s'", email));
    }

}
