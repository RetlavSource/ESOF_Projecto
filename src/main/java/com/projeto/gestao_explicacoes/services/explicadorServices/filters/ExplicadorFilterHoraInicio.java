package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Horario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterHoraInicio implements FilterExplicador {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private LocalTime horaInicio;

    public ExplicadorFilterHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {

        this.logger.info("No método: ExplicadorFilterHoraInicio -> filter");

        if ( this.horaInicio == null ) {
            return todosExplicadores;
        }

        Set<Explicador> explicadoresFiltrados = new HashSet<>();
        for (Explicador explicador : todosExplicadores) {
            for (Horario horario : explicador.getHorarios()) {
                if ( horario != null && this.horaInicio.getHour() >= horario.getHoraInicio().getHour()
                        && this.horaInicio.getHour() < horario.getHoraFim().getHour() ) {
                    explicadoresFiltrados.add(explicador);
                    break;
                }
            }
        }


        return explicadoresFiltrados;
    }
}
