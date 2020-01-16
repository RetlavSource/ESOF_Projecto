package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.services.atendimentoServices.AtendimentoService;
import com.projeto.gestao_explicacoes.services.atendimentoServices.filters.AtendimentoDTO;
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
@RequestMapping("/atendimento")
public class AtendimentoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Atendimento>> getAllAtendimentos() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.atendimentoService.findAll());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoDTO> createAtendimento(@RequestBody AtendimentoDTO objatendimento) {
        this.logger.info("Recebido um pedido POST");

        Optional<AtendimentoDTO> criadoAtendimento = this.atendimentoService.criarAtendimento(objatendimento);

        if(criadoAtendimento.isPresent()){
            return ResponseEntity.ok(criadoAtendimento.get());
        }
        throw new FalhaCriarException("O atendimento entre o explicador " + objatendimento.getNomeExplicador() + " e o aluno " + objatendimento.getNomeAluno() + " na data " + objatendimento.getData() + " nao foi criado com sucesso!");
    }
}
