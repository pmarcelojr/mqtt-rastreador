package com.phonerastreador.backend.exception;

import com.phonerastreador.backend.controller.dto.ErroDto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UsernameDuplicadoException.class, EmailDuplicadoException.class })
    protected ResponseEntity<Object> dadoUsuarioDuplicado(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErroDto(ex.getMessage()), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    
}
