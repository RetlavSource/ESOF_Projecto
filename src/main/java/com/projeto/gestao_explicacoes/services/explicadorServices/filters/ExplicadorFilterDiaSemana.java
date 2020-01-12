package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;

import java.time.DayOfWeek;
import java.util.Set;

public class ExplicadorFilterDiaSemana implements FilterExplicador {

    private DayOfWeek diaSemana;

    public ExplicadorFilterDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {
        return null;
    }
}
