package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Horario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterDiaSemana implements FilterExplicador {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private DayOfWeek diaSemana;

    public ExplicadorFilterDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {

        this.logger.info("No método: ExplicadorFilterDiaSemana -> filter");

        if ( this.diaSemana == null ) {
            return todosExplicadores;
        }

        Set<Explicador> explicadoresFiltrados = new HashSet<>();
        for (Explicador explicador : todosExplicadores) {
            for (Horario horario : explicador.getHorarios()) {
                if ( horario != null && horario.getDiaSemana().equals(this.diaSemana) ) {
                    explicadoresFiltrados.add(explicador);
                    break;
                }
            }
        }

        return explicadoresFiltrados;
    }
}
