package com.phonerastreador.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailDuplicadoException extends RuntimeException {

    public EmailDuplicadoException(String email) {
        super(String.format("Email já está em uso: '%s'", email));
    }

}
