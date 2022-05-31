package com.phonerastreador.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UsernameDuplicadoException extends RuntimeException {

    public UsernameDuplicadoException(String username) {
        super(String.format("Nome de usuário já está sendo utilizado: '%s'", username));
    }

}
