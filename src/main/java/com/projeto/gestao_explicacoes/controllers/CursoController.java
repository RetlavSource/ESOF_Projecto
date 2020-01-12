package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.services.cursoServices.CursoService;
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
@RequestMapping("/curso")
public class CursoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Curso>> getAllCursos() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.cursoService.findAll());
    }

    //Mandei objeto dentro de objeto pelo postman
    /** Como mandar o pedido POST na aplicação Postman
     *
     * Sem acentos no payload
     * no request POST do postman: localhost:8082/curso/1
     {
        "nome" : "Engenharia Civil",

        "faculdade" : [

            {"nome" : "Ciencias e Tecnologia"}
        ]
     }
     */
    @PostMapping(value = "/{faculdade}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> createCursoInFaculdade(@RequestBody Curso curso, @PathVariable("faculdade") String nomeFaculdade){
        this.logger.info("Recebido um pedido POST");

        Optional<Curso> criadoCursoFaculdade = this.cursoService.criarCursoFaculdade(curso,nomeFaculdade);

        if(criadoCursoFaculdade.isPresent()){
            return ResponseEntity.ok(criadoCursoFaculdade.get());
        }

        throw new FalhaCriarException("O curso de: " + curso.getNome() + " nao foi criado com sucesso na faculdade de: " + nomeFaculdade + "!");
    }
}
