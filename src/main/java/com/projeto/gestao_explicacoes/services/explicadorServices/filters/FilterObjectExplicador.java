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

}
