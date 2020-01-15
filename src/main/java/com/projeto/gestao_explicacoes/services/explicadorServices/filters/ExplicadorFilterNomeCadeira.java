package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Explicador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterNomeCadeira implements FilterExplicador {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private String nomeCadeira;

    public ExplicadorFilterNomeCadeira(String nomeCadeira) {
        this.nomeCadeira = nomeCadeira;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {

        this.logger.info("No método: ExplicadorFilterNomeCadeira -> filter");

        if ( this.nomeCadeira == null || this.nomeCadeira.isBlank() ) {
            return todosExplicadores;
        }

        Set<Explicador> explicadoresFiltrados = new HashSet<>();
        for (Explicador explicador : todosExplicadores) {
            for (Cadeira cadeira : explicador.getCadeiras()) {
                if ( cadeira != null && cadeira.getNome().equals(this.nomeCadeira) ) {
                    explicadoresFiltrados.add(explicador);
                    break;
                }
            }
        }

        return explicadoresFiltrados;
    }
}
