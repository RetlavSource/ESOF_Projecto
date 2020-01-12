package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;

import java.time.LocalTime;
import java.util.Set;

public class ExplicadorFilterHoraFim implements FilterExplicador {

    private LocalTime horaFim;

    public ExplicadorFilterHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {
        return null;
    }
}
