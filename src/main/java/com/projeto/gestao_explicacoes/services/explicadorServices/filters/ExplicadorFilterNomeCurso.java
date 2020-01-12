package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;

import java.util.Set;

public class ExplicadorFilterNomeCurso implements FilterExplicador {

    private String nomeCurso;

    public ExplicadorFilterNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {
        return null;
    }
}
