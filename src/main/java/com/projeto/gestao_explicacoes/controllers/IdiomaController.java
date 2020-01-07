package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.models.Idioma;
import com.projeto.gestao_explicacoes.services.idiomaServices.IdiomaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/idioma")
public class IdiomaController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private IdiomaService idiomaService;

    @Autowired
    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Idioma>> getAllIdiomas() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.idiomaService.findAll());
    }

}
