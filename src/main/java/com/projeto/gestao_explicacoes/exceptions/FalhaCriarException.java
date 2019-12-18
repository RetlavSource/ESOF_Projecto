package com.projeto.gestao_explicacoes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Falha ao Criar!")
public class FalhaCriarException extends RuntimeException {

    public FalhaCriarException(String message) {
        super(message);
    }

    /*
    public static void main(String[] args) {

        Map<String,String> mapping=new HashMap<>();
        mapping.put("UFP","http://localhost:8080");
        mapping.put("UP","http://localhost:8081");


        //
        String uni="UFP";
        StringBuilder sb=new StringBuilder();

        sb.append(mapping.get(uni)).append("/explicador");

        System.out.println(sb.toString());


    }
    */
}

