package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.services.explicadorServices.ExplicadorService;
import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * @param parametros capturados no url
     * @return Set com os explicadores encontrados
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ExplicadorDTO>> procuraDisponibilidadeExplicador(@RequestParam Map<String, String> parametros) {
        this.logger.info("Recebido um pedido GET com parâmetros em procuraDisponibilidadeExplicador()");


        return ResponseEntity.ok(this.explicadorService.procuraExplicadores(parametros));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> createExplicador(@RequestBody ExplicadorDTO explicadorDTO){
        this.logger.info("Recebido um pedido POST em createExplicador()");

        Optional<ExplicadorDTO> criadoExplicadorDTO = this.explicadorService.criarExplicador(explicadorDTO);

        if(criadoExplicadorDTO.isPresent()){
            return ResponseEntity.ok(criadoExplicadorDTO.get());
        }

        throw new FalhaCriarException("O explicador com o nome: " + explicadorDTO.getNome() + " ja existe!");
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
