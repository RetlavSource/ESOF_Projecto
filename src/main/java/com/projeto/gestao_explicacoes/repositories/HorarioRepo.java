package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Horario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface HorarioRepo extends CrudRepository<Horario, Long> {

    Optional<Horario> findByDiaSemana(DayOfWeek dayOfWeek);
    // Pesquisa, num determinado dia da semana, a disponibilidade entre hora de inicio e fim
    Optional<Horario> findByDiaSemanaAndHoraInicioIsGreaterThanEqualAndHoraFimIsLessThanEqual(DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim);

}
