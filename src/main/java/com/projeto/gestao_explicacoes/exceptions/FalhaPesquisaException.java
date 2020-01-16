package com.projeto.gestao_explicacoes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pesquisa NÃO conclusiva!")
public class FalhaPesquisaException extends RuntimeException {

    /**
     * Exception generalizada para ser invocada ao falhar uma pesquisa
     *
     * @param message mensagem criada pela função chamadora da exception
     */
    public FalhaPesquisaException(String message) {
        super(message);
    }
}