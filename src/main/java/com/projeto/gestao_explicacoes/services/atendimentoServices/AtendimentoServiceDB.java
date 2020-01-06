package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Horario;
import com.projeto.gestao_explicacoes.repositories.AlunoRepo;
import com.projeto.gestao_explicacoes.repositories.AtendimentoRepo;
import com.projeto.gestao_explicacoes.repositories.ExplicadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Profile(value = "db")
public class AtendimentoServiceDB implements AtendimentoService{

    private AtendimentoRepo atendimentoRepo;
    private AlunoRepo alunoRepo;
    private ExplicadorRepo explicadorRepo;

    @Autowired
    public AtendimentoServiceDB(AtendimentoRepo atendimentoRepo, AlunoRepo alunoRepo, ExplicadorRepo explicadorRepo){

        this.atendimentoRepo = atendimentoRepo;
        this.alunoRepo = alunoRepo;
        this.explicadorRepo = explicadorRepo;
    }

    @Override
    public Optional<Atendimento> criarAtendimento(Atendimento atendimento) {

        /*
        if(atendimento.getData() == null || atendimento.getAluno() == null || atendimento.getCadeira() == null || atendimento.getExplicador() == null || atendimento.getIdioma() == null){

            return Optional.empty();
        }
        */

        /*
        Explicador explicador = atendimento.getExplicador();
        Aluno aluno = atendimento.getAluno();
        int hour = atendimento.getData().getHour();

        for(Aluno alunoBd : this.alunoRepo.findAll()){

            if(!(aluno.getNumero().equals(alunoBd.getNumero()))){

                return Optional.empty();
            }
        }

        for(Explicador explicadorBd : this.explicadorRepo.findAll()){

            if(explicador.getNumero().equals(explicadorBd.getNumero())){

                for(Horario horario : explicadorBd.getHorarios()){

                    if(atendimento.getData().getDayOfWeek().equals(horario.getDiaSemana())){

                        int hora_incio = horario.getHoraInicio().getHour();
                        int hora_fim = horario.getHoraFim().getHour();

                        if(hour < horario.getHoraInicio().getHour()){


                        }

                    }else{

                        return Optional.empty();
                    }
                }
            }else{

                return Optional.empty();
            }


        }*/

        return Optional.empty();
    }
        
}
