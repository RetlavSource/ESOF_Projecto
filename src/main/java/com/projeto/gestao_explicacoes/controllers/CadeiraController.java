package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.services.cadeiraServices.CadeiraService;
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
@RequestMapping("/cadeira")
public class CadeiraController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private CadeiraService cadeiraService;

    @Autowired
    public CadeiraController(CadeiraService cadeiraService) {
        this.cadeiraService = cadeiraService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Cadeira>> getAllCadeiras() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.cadeiraService.findAll());
    }

}
