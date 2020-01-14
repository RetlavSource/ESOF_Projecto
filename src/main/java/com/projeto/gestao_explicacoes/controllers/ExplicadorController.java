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

    /**
     * Pesquisa os explicadores por parâmetros.
     * Caso se omita ou não existam valores nos parâmetros,
     * devolve todos os explicadores
     *
     * @param parametros capturador no url
     * @return explicadores encontrados
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ExplicadorDTO>> procuraDisponibilidadeExplicador(@RequestParam Map<String, String> parametros) {
        this.logger.info("Recebido um pedido GET com parâmetros em procuraDisponibilidadeExplicador()");

        System.out.println(parametros);

        for (Map.Entry<String, String> map : parametros.entrySet()) {
            if (map.getValue().equals("null")) {
                parametros.put(map.getKey(), "");
            }
        }

        System.out.println(parametros);
        System.out.println(parametros.isEmpty());
        System.out.println(parametros.size());

        String nomeCadeira = parametros.get("cadeira");
        String nomeIdioma = parametros.get("idioma");
        String diaSemana = parametros.get("dia");
        String horaInicio = parametros.get("inicio");
        String horaFim = parametros.get("fim");

        System.out.println(nomeCadeira+nomeIdioma+diaSemana+horaInicio+horaFim);

        if (nomeIdioma != null && !nomeIdioma.isBlank()) {
            nomeIdioma = nomeIdioma.toUpperCase();
        }

        DayOfWeek dia = null;
        if (diaSemana != null && !diaSemana.isBlank()) {
            dia = DayOfWeek.valueOf(diaSemana.toUpperCase());
        }

        LocalTime timeInit = null;
        LocalTime timeEnd = null;
        if (horaInicio != null && !horaInicio.isBlank()) {
            timeInit = LocalTime.parse(horaInicio);
        }
        if (horaFim != null && !horaFim.isBlank()) {
            timeEnd = LocalTime.parse(horaFim);
        }

        FilterObjectExplicador filterObjectExplicador = new FilterObjectExplicador(nomeCadeira, nomeIdioma, dia, timeInit, timeEnd);
        Set<ExplicadorDTO> explicadoresDisponiveis = this.explicadorService.procuraExplicadores(filterObjectExplicador);
        return ResponseEntity.ok(explicadoresDisponiveis);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador){

        this.logger.info("Recebido um pedido POST em createExplicador()");

        Optional<Explicador> criadoExplicador = this.explicadorService.criarExplicador(explicador);

        if(criadoExplicador.isPresent()){

            return ResponseEntity.ok(criadoExplicador.get());
        }

        throw new FalhaCriarException("O explicador com o nome: " + explicador.getNome() + " ja existe!");
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> putExplicadorHorario(@RequestBody ExplicadorDTO infoExplicador) {
        this.logger.info("Recebido um pedido PUT em putExplicadorHorario()");

        Optional<ExplicadorDTO> optExplicador = this.explicadorService.modificaExplicador(infoExplicador);

        if (optExplicador.isPresent()) {
            return ResponseEntity.ok(optExplicador.get());
        }

        throw new FalhaCriarException("Falha ao modificar as disponibilidades do explicador!");
    }

    @GetMapping(value = "/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> getExplicadorByNome(@PathVariable("nome") String nomeExplicador) {
        this.logger.info("Recebido um pedido GET em getExplicadorByNome()");

        Optional<ExplicadorDTO> optExplicador = this.explicadorService.findByNome(nomeExplicador);

        if (optExplicador.isPresent()) {
            return ResponseEntity.ok(optExplicador.get());
        }

        throw new FalhaCriarException("O explicador com o nome: " + nomeExplicador + " nao existe!");
    }

}
