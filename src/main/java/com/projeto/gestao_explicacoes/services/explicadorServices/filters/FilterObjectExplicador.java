package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterObjectExplicador {

    private String nomeCadeira;
    private String nomeIdioma;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    /**
     * Verifica se o objeto não contem valores (está vazio)
     *
     * @return true caso esteja vazio, ou false caso não esteja
     */
    public boolean isEmpty() {
        if (this.nomeCadeira == null || this.nomeCadeira.isBlank()) {
            if (this.nomeIdioma == null || this.nomeIdioma.isBlank()) {
                return this.diaSemana == null && this.horaInicio == null && this.horaFim == null;
            }
        }
        return false;
    }

}
