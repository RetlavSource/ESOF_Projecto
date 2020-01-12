package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;

import java.util.Set;

public class ExplicadorFilterNomeCadeira implements FilterExplicador {

    private String nomeCadeira;

    public ExplicadorFilterNomeCadeira(String nomeCadeira) {
        this.nomeCadeira = nomeCadeira;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {
        return null;
    }
}
