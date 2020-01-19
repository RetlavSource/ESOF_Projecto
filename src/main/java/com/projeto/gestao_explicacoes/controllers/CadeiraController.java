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

    /**
     * Cria uma {@code cadeira} num {@code curso}
     *
     * @param cadeira {@code cadeira} passado por POST, no payload
     * @param nomeCurso nome do curso passado no url
     * @return {@code cadeira} criada
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
