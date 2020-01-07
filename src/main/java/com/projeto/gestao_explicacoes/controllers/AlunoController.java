package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.exceptions.FalhaPesquisaException;
import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.services.alunoServices.AlunoService;
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
@RequestMapping("/aluno")
public class AlunoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    
    private AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService){

        this.alunoService = alunoService;
    }

    //@RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Aluno>> getAllAlunos(){
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.alunoService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") Long id){
        this.logger.info("Recebido um pedido GET");

        Optional<Aluno> optionalAluno = this.alunoService.findById(id);
        if (optionalAluno.isPresent()){
            return ResponseEntity.ok(optionalAluno.get());
        }
        //return ResponseEntity.notFound().build();
        throw new FalhaPesquisaException("Aluno com o id: " + id + " inexistente!");
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno){
        this.logger.info("Recebido um pedido POST");

        Optional<Aluno> criadoAluno = this.alunoService.criarAluno(aluno);

        if(criadoAluno.isPresent()){

            return ResponseEntity.ok(criadoAluno.get());
        }

        throw new FalhaCriarException("O aluno com o nome: " + aluno.getNome() + " ja existe!");
    }


}









