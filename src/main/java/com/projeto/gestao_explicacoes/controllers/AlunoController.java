package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.repositories.AlunoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AlunoRepo alunoRepo;

    //@RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Aluno>> getAllAlunos(){
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.alunoRepo.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") Long id){
        this.logger.info("Recebido um pedido GET");

        Optional<Aluno> optionalAluno = this.alunoRepo.findById(id);
        if (optionalAluno.isPresent()){
            return ResponseEntity.ok(optionalAluno.get());
        }
        //return ResponseEntity.notFound().build();
        throw new NoAlunoException(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno){
        this.logger.info("Recebido um pedido POST");

        // O mesmo que é feito no método "getAlunosById", mas mais compacto
        if (this.alunoRepo.findByNumero(aluno.getNumero()).isPresent()){
            //return ResponseEntity.badRequest().build();
            throw new AlunoAlreadyExistsException(aluno.getNome());
        }
        //System.out.println(aluno);
        //return ResponseEntity.ok().build();
        return ResponseEntity.ok(this.alunoRepo.save(aluno));
    }





    // *******************************************
    // ****** RESPONSE STATUS -- EXCEPTIONS ******
    // *******************************************

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Aluno inexistente!")
    private class NoAlunoException extends RuntimeException{

        public NoAlunoException(Long id){
            super("Aluno inexistente com o id: " + id);
        }

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Aluno ja existe!")
    private class AlunoAlreadyExistsException extends RuntimeException{

        public AlunoAlreadyExistsException(String nome){
            super("O aluno com o nome: " + nome + " ja existe!");
        }

    }

}









