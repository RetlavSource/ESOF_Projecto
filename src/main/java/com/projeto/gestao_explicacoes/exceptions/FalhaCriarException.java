package com.projeto.gestao_explicacoes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Falha ao Criar!")
public class FalhaCriarException extends RuntimeException {

    public FalhaCriarException(String message) {
        super(message);
    }
}

