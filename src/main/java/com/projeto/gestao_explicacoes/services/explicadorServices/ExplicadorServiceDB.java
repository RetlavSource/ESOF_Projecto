package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.*;
import com.projeto.gestao_explicacoes.models.builders.ExplicadorBuilder;
import com.projeto.gestao_explicacoes.repositories.*;
import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.FilterExplicadorService;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.FilterObjectExplicador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

@Service
@Profile(value = "db")
public class ExplicadorServiceDB implements ExplicadorService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private ExplicadorRepo explicadorRepo;
    private HorarioRepo horarioRepo;
    private IdiomaRepo idiomaRepo;
    private AtendimentoRepo atendimentoRepo;
    private CadeiraRepo cadeiraRepo;
    private FilterExplicadorService filterExplicadorService;

    @Autowired
    public ExplicadorServiceDB(ExplicadorRepo explicadorRepo, HorarioRepo horarioRepo, IdiomaRepo idiomaRepo, AtendimentoRepo atendimentoRepo, CadeiraRepo cadeiraRepo, FilterExplicadorService filterExplicadorService) {
        this.explicadorRepo = explicadorRepo;
        this.horarioRepo = horarioRepo;
        this.idiomaRepo = idiomaRepo;
        this.atendimentoRepo = atendimentoRepo;
        this.cadeiraRepo = cadeiraRepo;
        this.filterExplicadorService = filterExplicadorService;
    }

    @Override
    public Set<Explicador> findAll() {
        Set<Explicador> explicadores = new HashSet<>();
        for (Explicador explicador : this.explicadorRepo.findAll()) {
            explicadores.add(explicador);
        }
        return explicadores;
    }

    @Override
    public Optional<ExplicadorDTO> criarExplicador(ExplicadorDTO explicadorDTO) {
        this.logger.info("No método: ExplicadorServiceDB -> criarExplicador");

        if(this.explicadorRepo.findByNumero(explicadorDTO.getNumero()).isPresent()){
            return Optional.empty();
        }

        Explicador novoExplicador = new ExplicadorBuilder()
                .setNome(explicadorDTO.getNome())
                .setNumero(explicadorDTO.getNumero())
                .setHorario(explicadorDTO.getHorarios())
                .setIdiomas(explicadorDTO.getIdiomas())
                .setAtendimentos(explicadorDTO.getAtendimentos())
                .setCadeiras(explicadorDTO.getCadeiras())
                .build();

        this.explicadorRepo.save(novoExplicador);

        ExplicadorDTO auxExplicadorDTO = novoExplicador.copyToExplicadorDTO();
        auxExplicadorDTO.allSetToDTO();
        return Optional.of(auxExplicadorDTO);
    }

    @Override
    public Optional<ExplicadorDTO> findByNome(String nomeExplicador) {

        Optional<Explicador> optExplicador = this.explicadorRepo.findByNome(nomeExplicador);

        if ( optExplicador.isEmpty() ) {
            return Optional.empty();
        }

        return Optional.of(new ExplicadorDTO(optExplicador.get().getNome(), optExplicador.get().getNumero()));
    }

    @Override
    public Optional<ExplicadorDTO> modificaExplicador(ExplicadorDTO infoExplicador) {
        this.logger.info("No método: ExplicadorServiceDB -> modificaExplicador");

        // Previne que o explicador não tenha numero e nome
        if (infoExplicador.getNumero() == 0 || infoExplicador.getNumero() == null || infoExplicador.getNome() == null) {
            this.logger.info("Explicador sem Nome ou Numero!");
            return Optional.empty();
        }

        if (infoExplicador.getNome().isBlank()) {
            this.logger.info("Explicador sem Nome válido!");
            return Optional.empty();
        }

        Optional<Explicador> optExplicador = this.explicadorRepo.findByNumero(infoExplicador.getNumero());

        // Não existindo o explicador, ele é criado
        if (optExplicador.isEmpty()) {
            this.logger.info("A criar novo explicador!");

            return this.criarExplicador(infoExplicador);
        }

        this.logger.info("Atualizar explicador existente!");

        Explicador explicador = optExplicador.get();
        explicador.setNome(infoExplicador.getNome());
        explicador.setNumero(infoExplicador.getNumero());

        if ( !infoExplicador.getHorarios().isEmpty() ) {
            for (Horario horario: infoExplicador.getHorarios()) {
                if (explicador.containsHorario(horario)) {
                    continue;
                }
                explicador.addHorario(horario);
                this.horarioRepo.save(horario);
            }
        }

        if ( !infoExplicador.getIdiomas().isEmpty() ) {
            for (Idioma idioma : infoExplicador.getIdiomas()) {
                if (explicador.containsIdioma(idioma)) {
                    continue;
                }
                Idioma auxIdioma = new Idioma(idioma.getNome(), idioma.getSigla());
                explicador.addIdioma(auxIdioma);
                this.idiomaRepo.save(auxIdioma);
            }
        }

        if ( !infoExplicador.getAtendimentos().isEmpty() ) {
            for (Atendimento atendimento: infoExplicador.getAtendimentos()) {
                if (explicador.containsAtendimento(atendimento)) {
                    continue;
                }
                explicador.addAtendimento(atendimento);
                this.atendimentoRepo.save(atendimento);
            }
        }

        if ( !infoExplicador.getCadeiras().isEmpty() ) {
            for (Cadeira cadeira: infoExplicador.getCadeiras()) {
                if (explicador.containsCadeira(cadeira)) {
                    continue;
                }
                explicador.addCadeira(cadeira);
                this.cadeiraRepo.save(cadeira);
            }
        }

        this.explicadorRepo.save(explicador);

        ExplicadorDTO explicadorDTO = explicador.copyToExplicadorDTO();
        explicadorDTO.allSetToDTO();
        return Optional.of(explicadorDTO);
    }

    @Override
    public Set<ExplicadorDTO> procuraExplicadores(Map<String, String> parametros) {
        this.logger.info("No método: ExplicadorServiceDB -> procuraExplicadores");

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
            diaSemana = diaSemana.toUpperCase();
            switch (diaSemana) {
                case "MONDAY":
                case "TUESDAY":
                case "WEDNESDAY":
                case "THURSDAY":
                case "FRIDAY":
                case "SATURDAY":
                case "SUNDAY":
                    dia = DayOfWeek.valueOf(diaSemana);
                    break;
                case "SEGUNDA":{
                    dia = DayOfWeek.valueOf("MONDAY");
                    break;
                }
                case "TERCA":{
                    dia = DayOfWeek.valueOf("TUESDAY");
                    break;
                }
                case "QUARTA":{
                    dia = DayOfWeek.valueOf("WEDNESDAY");
                    break;
                }
                case "QUINTA":{
                    dia = DayOfWeek.valueOf("THURSDAY");
                    break;
                }
                case "SEXTA":{
                    dia = DayOfWeek.valueOf("FRIDAY");
                    break;
                }
                case "SABADO":{
                    dia = DayOfWeek.valueOf("SATURDAY");
                    break;
                }
                case "DOMINGO": {
                    dia = DayOfWeek.valueOf("SUNDAY");
                    break;
                }
            }
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

        if (filterObjectExplicador.isEmpty()) {
            Set<ExplicadorDTO> explicadorTransfer = new HashSet<>();
            for (Explicador explicador : this.findAll()) {
                explicadorTransfer.add(explicador.copyToExplicadorDTO());
            }
            return explicadorTransfer;
        }

        if (filterObjectExplicador.getHoraInicio() != null) {
            if (filterObjectExplicador.getHoraInicio().getMinute() != 0) {
                throw new FalhaCriarException("Só existem horas certas, logo os minutos tem de ser sempre 0!! Ex: 13:00 !!");
            }
        }
        if (filterObjectExplicador.getHoraFim() != null) {
            if (filterObjectExplicador.getHoraFim().getMinute() != 0) {
                throw new FalhaCriarException("Só existem horas certas, logo os minutos tem de ser sempre 0!! Ex: 13:00 !!");
            }
        }

        if ( filterObjectExplicador.getHoraInicio() != null && filterObjectExplicador.getHoraFim() != null ) {
            if ( filterObjectExplicador.getHoraInicio().getHour() > filterObjectExplicador.getHoraFim().getHour() ) {
                throw new FalhaCriarException("A Hora de Inicio não pode ser superior a Hora de Fim!!");
            }
        }

        Set<Explicador> todosExplicadores = this.findAll();

        //return this.filterExplicadorService.preFilter(todosExplicadores, filterObjectExplicador);

        Set<ExplicadorDTO> explicadorTransfer = new HashSet<>();
        for (Explicador explicador : this.filterExplicadorService.preFilter(todosExplicadores, filterObjectExplicador)) {
            explicadorTransfer.add(explicador.copyToExplicadorDTO());
        }
        return explicadorTransfer;
    }

}
