package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;

import java.time.LocalTime;
import java.util.Set;

public class ExplicadorFilterHoraInicio implements FilterExplicador {

    private LocalTime horaInicio;

    public ExplicadorFilterHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {
        return null;
    }
}
