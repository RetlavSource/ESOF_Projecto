package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.services.atendimentoServices.AtendimentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService){

        this.atendimentoService = atendimentoService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Atendimento> createAtendimento(@RequestBody Atendimento atendimento){

        this.logger.info("Recebido um pedido POST");

        Optional<Atendimento> criadoAtendimento = this.atendimentoService.criarAtendimento(atendimento);

        if(criadoAtendimento.isPresent()){

            return ResponseEntity.ok(criadoAtendimento.get());
        }

        throw new FalhaCriarException("O atendimento no dia: " + atendimento.getData().getDayOfYear() + "nao foi possivel ser criado!");
    }
}
