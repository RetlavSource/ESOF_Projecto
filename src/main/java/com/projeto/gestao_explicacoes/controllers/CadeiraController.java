package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.services.cadeiraServices.CadeiraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

    //Mandei objeto dentro de objeto pelo postman
    /** Como mandar o pedido POST na aplicação Postman
     *
     * Sem acentos no payload
     * no request POST do postman: localhost:8082/cadeira/1
     {
        "nome" : "Inteligencia Artificial",
        "sigla": "IA",

        "curso" : [

            {"nome" : "Engenharia Informatica"}
        ]
     }
     */
    @PostMapping(value = "/{curso}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cadeira> createCadeiraInCurso(@RequestBody Cadeira cadeira, @PathVariable("curso") String nomeCurso){
        this.logger.info("Recebido um pedido POST");

        Optional<Cadeira> criadaCadeiraCurso = this.cadeiraService.criarCadeiraCurso(cadeira,nomeCurso);

        if(criadaCadeiraCurso.isPresent()){
            return ResponseEntity.ok(criadaCadeiraCurso.get());
        }

        throw new FalhaCriarException("A cadeira de: " + cadeira.getNome() + " nao foi criado com sucesso no curso de: " + nomeCurso + "!");
    }
}
