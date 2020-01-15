package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Idioma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorFilterNomeIdioma implements FilterExplicador {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private String nomeIdioma;

    public ExplicadorFilterNomeIdioma(String nomeIdioma) {
        this.nomeIdioma = nomeIdioma;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> todosExplicadores) {

        this.logger.info("No método: ExplicadorFilterNomeIdioma -> filter");

        if ( this.nomeIdioma == null || this.nomeIdioma.isBlank() ) {
            return todosExplicadores;
        }

        Set<Explicador> explicadoresFiltrados = new HashSet<>();
        for (Explicador explicador : todosExplicadores) {
            for (Idioma idioma : explicador.getIdiomas()) {
                if ( idioma != null && idioma.getNome().equals(this.nomeIdioma) ) {
                    explicadoresFiltrados.add(explicador);
                    break;
                }
            }
        }

        return explicadoresFiltrados;
    }
}
