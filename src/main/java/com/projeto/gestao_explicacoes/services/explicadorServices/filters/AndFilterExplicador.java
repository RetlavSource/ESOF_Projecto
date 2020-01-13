package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class AndFilterExplicador implements FilterExplicador {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private FilterExplicador primeiroFilter;
    private FilterExplicador segundoFiltro;

    public AndFilterExplicador(FilterExplicador primeiroFilter, FilterExplicador segundoFiltro) {
        this.primeiroFilter = primeiroFilter;
        this.segundoFiltro = segundoFiltro;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {

        this.logger.info("No método: AndFilterExplicador -> filter");

        Set<Explicador> filtro = this.primeiroFilter.filter(todosExplicadores);
        return this.segundoFiltro.filter(filtro);
    }
}
