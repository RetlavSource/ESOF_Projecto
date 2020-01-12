package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;

import java.util.Set;

public interface FilterExplicador {
    Set<Explicador> filter(Set<Explicador> todosExplicadores);
}
