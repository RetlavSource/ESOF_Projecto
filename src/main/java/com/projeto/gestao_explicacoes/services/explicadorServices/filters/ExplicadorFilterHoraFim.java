package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Horario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterHoraFim implements FilterExplicador {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private LocalTime horaFim;

    public ExplicadorFilterHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {

        this.logger.info("No método: ExplicadorFilterHoraFim -> filter");

        if ( this.horaFim == null ) {
            return todosExplicadores;
        }

        Set<Explicador> explicadoresFiltrados = new HashSet<>();
        for (Explicador explicador : todosExplicadores) {
            for (Horario horario : explicador.getHorarios()) {
                if ( horario != null && this.horaFim.getHour() <= horario.getHoraFim().getHour()
                        && this.horaFim.getHour() >= horario.getHoraInicio().getHour() ) {
                    explicadoresFiltrados.add(explicador);
                    break;
                }
            }
        }


        return explicadoresFiltrados;
    }
}
