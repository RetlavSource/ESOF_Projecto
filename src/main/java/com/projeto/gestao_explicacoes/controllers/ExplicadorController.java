package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.services.explicadorServices.ExplicadorService;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.ExplicadorDTO;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.FilterObjectExplicador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private ExplicadorService explicadorService;

    @Autowired
    public ExplicadorController(ExplicadorService explicadorService) {
        this.explicadorService = explicadorService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Explicador>> getAllExplicadores() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.explicadorService.findAll());
    }

    @GetMapping(value = "/procura", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Explicador>> procuraDisponibilidadeExplicador(@RequestParam Map<String, String> parametros) {
        this.logger.info("Recebido um pedido GET para /procura");

        String nomeCurso = parametros.get("nomeCurso");
        String nomeCadeira = parametros.get("nomeCadeira");
        String diaSemana = parametros.get("diaSemana");
        String horaInicio = parametros.get("horaInicio");
        String horaFim = parametros.get("horaFim");
        DayOfWeek dia = null;
        if (diaSemana != null) {
            dia = DayOfWeek.valueOf(diaSemana.toUpperCase());
        }
        LocalTime timeInit = null;
        LocalTime timeEnd = null;
        if (horaInicio != null) {
            timeInit = LocalTime.parse(horaInicio);
        }
        if (horaFim != null) {
            timeEnd = LocalTime.parse(horaFim);
        }

        FilterObjectExplicador filterObjectExplicador = new FilterObjectExplicador(nomeCurso, nomeCadeira, dia, timeInit, timeEnd);
        Set<Explicador> explicadoresDisponiveis = this.explicadorService.procuraExplicadores(filterObjectExplicador);
        return ResponseEntity.ok(explicadoresDisponiveis);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador){

        this.logger.info("Recebido um pedido POST");

        Optional<Explicador> criadoExplicador = this.explicadorService.criarExplicador(explicador);

        if(criadoExplicador.isPresent()){

            return ResponseEntity.ok(criadoExplicador.get());
        }

        throw new FalhaCriarException("O explicador com o nome: " + explicador.getNome() + " ja existe!");
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> putExplicadorHorario(@RequestBody ExplicadorDTO infoExplicador) {
        this.logger.info("Recebido um pedido PUT");

        Optional<ExplicadorDTO> optExplicador = this.explicadorService.modificaExplicador(infoExplicador);

        if (optExplicador.isPresent()) {
            return ResponseEntity.ok(optExplicador.get());
        }

        throw new FalhaCriarException("Falha ao modificar as disponibilidades do explicador!");
    }

    @GetMapping(value = "/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> getExplicadorByNome(@PathVariable("nome") String nomeExplicador) {
        this.logger.info("Recebido um pedido GET");

        Optional<ExplicadorDTO> optExplicador = this.explicadorService.findByNome(nomeExplicador);

        if (optExplicador.isPresent()) {
            return ResponseEntity.ok(optExplicador.get());
        }

        throw new FalhaCriarException("O explicador com o nome: " + nomeExplicador + " nao existe!");
    }
}
