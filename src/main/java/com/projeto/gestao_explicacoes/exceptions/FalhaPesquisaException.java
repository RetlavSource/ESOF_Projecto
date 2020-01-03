package com.projeto.gestao_explicacoes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pesquisa NÃO conclusiva!")
public class FalhaPesquisaException extends RuntimeException {

    public FalhaPesquisaException(String message) {
        super(message);
    }
}