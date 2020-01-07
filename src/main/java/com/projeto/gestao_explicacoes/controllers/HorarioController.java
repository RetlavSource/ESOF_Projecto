package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.models.Horario;
import com.projeto.gestao_explicacoes.services.horarioServices.HorarioService;
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
@RequestMapping("/horario")
public class HorarioController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private HorarioService horarioService;

    @Autowired
    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Horario>> getAllHorarios() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.horarioService.findAll());
    }

}
