package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.repositories.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepo alunoRepo;

    //@RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Aluno>> getAllAlunos(){

        return ResponseEntity.ok(this.alunoRepo.findAll());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id){

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno){

        System.out.println(aluno);

        return ResponseEntity.ok().build();
    }

}









