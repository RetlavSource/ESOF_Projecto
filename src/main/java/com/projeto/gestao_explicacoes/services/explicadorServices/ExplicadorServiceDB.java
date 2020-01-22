package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.*;
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

    /**
     * Cria um explicador.
     *
     * @param explicadorDTO recebido
     * @return {@code Optional.of(explicadorDTO)} se criado ou {@code Optional.empty()} se não
     */
    @Override
    public Optional<ExplicadorDTO> criarExplicador(ExplicadorDTO explicadorDTO) {
        this.logger.info("No método: ExplicadorServiceDB -> criarExplicador");

        if (this.explicadorRepo.findByNumero(explicadorDTO.getNumero()).isPresent()){
            return Optional.empty();
        }

        Explicador explicador = new Explicador(explicadorDTO.getNome(), explicadorDTO.getNumero());
        this.explicadorRepo.save(explicador);

        this.adicionaHorariosIdiomasAtendimentosCadeiras(explicador, explicadorDTO);

        ExplicadorDTO auxExplicadorDTO = explicador.copyToExplicadorDTO();
        auxExplicadorDTO.allSetToDTO();
        return Optional.of(auxExplicadorDTO);
    }

    /**
     * Pesquisa o explicador pelo nome.
     *
     * @param nomeExplicador nome do explicador
     * @return {@code Optional.of(explicadorDTO)} se encontrado ou {@code Optional.empty()} se não
     */
    @Override
    public Optional<ExplicadorDTO> findByNome(String nomeExplicador) {

        Optional<Explicador> optExplicador = this.explicadorRepo.findByNome(nomeExplicador);

        if ( optExplicador.isEmpty() ) {
            return Optional.empty();
        }

        ExplicadorDTO auxExplicador = optExplicador.get().copyToExplicadorDTO();
        auxExplicador.allSetToDTO();

        return Optional.of(auxExplicador);
    }

    /**
     * Modifica os campos de um explicador.
     * Se o explicador não existir, cria-o.
     *
     * @param infoExplicador recebido
     * @return {@code Optional.of(explicadorDTO)} se modificado/criado ou {@code Optional.empty()} se não
     */
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

        this.adicionaHorariosIdiomasAtendimentosCadeiras(explicador, infoExplicador);

        ExplicadorDTO explicadorDTO = explicador.copyToExplicadorDTO();
        explicadorDTO.allSetToDTO();
        return Optional.of(explicadorDTO);
    }

    /**
     * Procura um explicador por diversos parâmetros, utilizando filtros.
     *
     * @param parametros de pesquisa
     * @return {@code Set<ExplicadorDTO>} com os explicadores encontrados
     */
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
            this.logger.info("A devolver todos os explicadores!");
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
        this.logger.info("A devolver o resultado da pesquisa!");
        return explicadorTransfer;
    }

    /**
     * Adiciona uma ou mais cadeiras ao explicador.
     *
     * @param infoExplicador explicador a adicionar a cadeira
     * @param nomeCadeira nome da cadeira
     * @return {@code Optional<ExplicadorDTO>} com o explicador
     */
    @Override
    public Optional<ExplicadorDTO> adicionaCadeiraAoExplicador(ExplicadorDTO infoExplicador, String nomeCadeira) {
        this.logger.info("No método: ExplicadorServiceDB -> adicionaCursoAoExplicador");

        Optional<Explicador> optExplicador = this.explicadorRepo.findByNumero(infoExplicador.getNumero());

        if (optExplicador.isPresent()) {
            Optional<Cadeira> optCadeira = this.cadeiraRepo.findByNome(nomeCadeira);
            if (optCadeira.isPresent()) {
                this.logger.info("A associar a cadeira ao explicador!");
                optExplicador.get().addCadeira(optCadeira.get());
                this.explicadorRepo.save(optExplicador.get());
                ExplicadorDTO explicadorDTO = optExplicador.get().copyToExplicadorDTO();
                explicadorDTO.allSetToDTO();
                return Optional.of(explicadorDTO);
            } else {
                throw new FalhaCriarException("Não existe a cadeira com o nome indicado!!");
            }
        } else {
            throw new FalhaCriarException("Não existe o explicador indicado!!");
        }
    }

    /**
     * Adiciona/Cria no explicador: {@code horarios}e {@code idiomas}.
     * Adiciona ao explicador: {@code atendimentos} e {@code cadeiras}.
     * Inserir {@code null} no parâmetro que não deseja utilizar.
     * <i><b>Persiste</b></i> o explicador na base de dados.
     *
     * @param explicador previamente <i><b>persistido</b></i> na base de dados
     * @param explicadorDTO utiliza os parâmetros {@code horarios}, {@code idiomas}, {@code atendimentos} e {@code cadeiras}
     */
    private void adicionaHorariosIdiomasAtendimentosCadeiras(Explicador explicador, ExplicadorDTO explicadorDTO) {
        this.logger.info("No método: ExplicadorServiceDB -> adicionaHorariosIdiomasAtendimentosCadeiras");

        if (explicadorDTO.getHorarios() != null) {
            if ( !explicadorDTO.getHorarios().isEmpty() ) {
                for (Horario horario: explicadorDTO.getHorarios()) {
                    if (explicador.containsHorario(horario)) {
                        continue;
                    }
                    if (!horario.isHoraInicioAndHoraFimValid()) {
                        throw new FalhaCriarException("O horário introduzido não è válido!!");
                    }
                    explicador.addHorario(horario);
                    this.horarioRepo.save(horario);
                    this.logger.info("Adicionado Horário!");
                }
            }
        }

        if (explicadorDTO.getIdiomas() != null) {
            if ( !explicadorDTO.getIdiomas().isEmpty() ) {
                for (Idioma idioma : explicadorDTO.getIdiomas()) {
                    if (explicador.containsIdioma(idioma)) {
                        continue;
                    }
                    Optional <Idioma> idiomaCheck = this.idiomaRepo.findByNome(idioma.getNome().toUpperCase());
                    if (idiomaCheck.isPresent()) {
                        explicador.addIdioma(idiomaCheck.get());
                        this.idiomaRepo.save(idiomaCheck.get());
                        this.logger.info("Adicionado Idioma!");
                    } else {
                        Idioma auxIdioma = new Idioma(idioma.getNome(), idioma.getSigla());
                        explicador.addIdioma(auxIdioma);
                        this.idiomaRepo.save(auxIdioma);
                        this.logger.info("Criado Idioma!");
                    }
                }
            }
        }

        if (explicadorDTO.getAtendimentos() != null) {
            if ( !explicadorDTO.getAtendimentos().isEmpty() ) {
                for (Atendimento atendimento: explicadorDTO.getAtendimentos()) {
                    if (explicador.containsAtendimento(atendimento)) {
                        continue;
                    }
                    explicador.addAtendimento(atendimento);
                    this.atendimentoRepo.save(atendimento);
                    this.logger.info("Adicionado Atendimento!");
                }
            }
        }

        if (explicadorDTO.getCadeiras() != null) {
            if ( !explicadorDTO.getCadeiras().isEmpty() ) {
                for (Cadeira cadeira: explicadorDTO.getCadeiras()) {
                    if (explicador.containsCadeira(cadeira)) {
                        continue;
                    }
                    Optional<Cadeira> cadeiraCheck = this.cadeiraRepo.findByNome(cadeira.getNome());
                    if (cadeiraCheck.isPresent()) {
                        explicador.addCadeira(cadeiraCheck.get());
                        this.cadeiraRepo.save(cadeiraCheck.get());
                        this.logger.info("Adicionada Cadeira!");
                    } else {
                        throw new FalhaCriarException("Não existe a cadeira com o nome que indicou!!");
                    }
                }
            }
        }

        this.logger.info("A salvar explicador!");

        this.explicadorRepo.save(explicador);

    }
}
