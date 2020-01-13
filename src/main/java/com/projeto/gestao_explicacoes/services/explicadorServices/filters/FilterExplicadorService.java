package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.projeto.gestao_explicacoes.models.Explicador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilterExplicadorService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public Set<Explicador> preFilter(Set<Explicador> todosExplicadores, FilterObjectExplicador filterObjectExplicador) {

        this.logger.info("No método: FilterExplicadorService -> preFilter");

        FilterExplicador nomeCadeira = new ExplicadorFilterNomeCadeira(filterObjectExplicador.getNomeCadeira());
        FilterExplicador nomeIdioma = new ExplicadorFilterNomeIdioma(filterObjectExplicador.getNomeIdioma());
        FilterExplicador diaSemana = new ExplicadorFilterDiaSemana(filterObjectExplicador.getDiaSemana());
        FilterExplicador horaInicio = new ExplicadorFilterHoraInicio(filterObjectExplicador.getHoraInicio());
        FilterExplicador horaFim= new ExplicadorFilterHoraFim(filterObjectExplicador.getHoraFim());

        FilterExplicador nomeCadeiraAndNomeIdioma = new AndFilterExplicador(nomeCadeira, nomeIdioma);

        FilterExplicador nomeCadeiraAndNomeIdiomaAndDiaSemana = new AndFilterExplicador(nomeCadeiraAndNomeIdioma, diaSemana);
        FilterExplicador horaInicioAndHoraFim = new AndFilterExplicador(horaInicio, horaFim);

        return new AndFilterExplicador(nomeCadeiraAndNomeIdiomaAndDiaSemana, horaInicioAndHoraFim).filter(todosExplicadores);
    }
}
