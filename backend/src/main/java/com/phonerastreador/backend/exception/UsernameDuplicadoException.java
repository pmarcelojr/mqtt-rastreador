package com.phonerastreador.backend.exception;

public class UsernameDuplicadoException extends RuntimeException {

    public UsernameDuplicadoException(String username) {
        super(String.format("Nome de usuário já está sendo utilizado: '%s'", username));
    }

}
