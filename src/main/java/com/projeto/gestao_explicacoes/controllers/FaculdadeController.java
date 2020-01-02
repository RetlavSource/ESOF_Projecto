package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaPesquisaException;
import com.projeto.gestao_explicacoes.models.Faculdade;
import com.projeto.gestao_explicacoes.services.faculdadeServices.FaculdadeService;
import com.projeto.gestao_explicacoes.services.faculdadeServices.FaculdadeServiceDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private FaculdadeService faculdadeService;

    @Autowired
    public FaculdadeController(FaculdadeService faculdadeService) {

        this.faculdadeService = faculdadeService;
        //this.faculdadeService = new FaculdadeServiceDB();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Faculdade>> getAllFaculdades() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.faculdadeService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> getFaculdadeById(@PathVariable("id") Long id) {
        this.logger.info("Recebido um pedido GET");

        Optional<Faculdade> faculdade = this.faculdadeService.findById(id);

        if (faculdade.isPresent()) {
            return ResponseEntity.ok(faculdade.get());
        }

        throw new FalhaPesquisaException("Explicador com o id: " + id + " inexistente!");

    }
}
