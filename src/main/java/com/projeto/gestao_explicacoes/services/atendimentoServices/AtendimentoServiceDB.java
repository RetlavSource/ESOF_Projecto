package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.*;
import com.projeto.gestao_explicacoes.models.builders.AtendimentoBuilder;
import com.projeto.gestao_explicacoes.repositories.AlunoRepo;
import com.projeto.gestao_explicacoes.repositories.AtendimentoRepo;
import com.projeto.gestao_explicacoes.repositories.ExplicadorRepo;
import com.projeto.gestao_explicacoes.services.atendimentoServices.filters.AtendimentoObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class AtendimentoServiceDB implements AtendimentoService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());


    private AtendimentoRepo atendimentoRepo;
    private AlunoRepo alunoRepo;
    private ExplicadorRepo explicadorRepo;

    @Autowired
    public AtendimentoServiceDB(AtendimentoRepo atendimentoRepo, AlunoRepo alunoRepo, ExplicadorRepo explicadorRepo) {
        this.atendimentoRepo = atendimentoRepo;
        this.alunoRepo = alunoRepo;
        this.explicadorRepo = explicadorRepo;
    }

    @Override
    public Set<Atendimento> findAll() {
        Set<Atendimento> atendimentos = new HashSet<>();
        for (Atendimento atendimento: this.atendimentoRepo.findAll()) {
            atendimentos.add(atendimento);
        }
        return atendimentos;
    }

    @Override
    public Optional<AtendimentoObject> criarAtendimento(AtendimentoObject objAtendimento) {
        this.logger.info("No método: AtendimentoServiceDB -> criarAtendimento");

        Aluno alunoAux = null;
        Explicador explicadorAux = null;
        Cadeira cadeiraAux = null;
        Idioma idiomaAux = null;

        Optional<Aluno> aluno = this.alunoRepo.findByNome(objAtendimento.getNomeAluno());
        if (aluno.isEmpty()) {
            this.logger.info("Aluno inexistente!");
            return Optional.empty();
        }
        alunoAux = aluno.get();

        Optional<Explicador> explicador = this.explicadorRepo.findByNome(objAtendimento.getNomeExplicador());
        if (explicador.isEmpty()) {
            this.logger.info("Explicador inexistente!");
            return Optional.empty();
        }
        explicadorAux = explicador.get();

        for (Cadeira cadeira : explicadorAux.getCadeiras()) {
            if (cadeira.getNome().equals(objAtendimento.getNomeCadeira())) {
                cadeiraAux = cadeira;
                break;
            }
        }
        if (cadeiraAux == null) {
            this.logger.info("Cadeira inexistente!");
            return Optional.empty();
        }

        for (Idioma idioma : explicadorAux.getIdiomas()) {
            if (idioma.getNome().equals(objAtendimento.getNomeIdioma().toUpperCase())) {
                idiomaAux = idioma;
                break;
            }
        }
        if (idiomaAux == null) {
            this.logger.info("Idioma inexistente!");
            return Optional.empty();
        }

        for (Horario horario: explicadorAux.getHorarios()){
            this.logger.info("Horario OK!");

            if (horario.getDiaSemana().equals(objAtendimento.getData().getDayOfWeek())) {
                this.logger.info("Dia semana OK!");

                if ( (horario.getHoraInicio().getHour() <= objAtendimento.getData().getHour()) &&
                        (horario.getHoraFim().getHour() > objAtendimento.getData().getHour()) ) {
                    this.logger.info("Dif horas OK!");

                    for (Atendimento atendimento : explicadorAux.getAtendimentos()) {
                        if (atendimento.getData().equals(objAtendimento.getData())) {
                            this.logger.info("Já existe atendimento na data indicada!");
                            return Optional.empty();
                        }
                    }
                    this.logger.info("Atendimento OK!");

                    Atendimento newAtendimento = new AtendimentoBuilder()
                            .setData(objAtendimento.getData())
                            .setExplicador(explicadorAux)
                            .setAluno(alunoAux)
                            .setCadeira(cadeiraAux)
                            .setIdioma(idiomaAux)
                            .build();
                    this.atendimentoRepo.save(newAtendimento);

                    return Optional.of(objAtendimento);
                }
            }
        }

        return Optional.empty();
    }
}
