package com.projeto.gestao_explicacoes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Falha ao Criar!")
public class FalhaCriarException extends RuntimeException {

    /**
     * Exception generalizada para ser invocada ao falhar a criação de algum objeto
     *
     * @param message mensagem criada pela função chamadora da exception
     */
    public FalhaCriarException(String message) {
        super(message);
    }
}

