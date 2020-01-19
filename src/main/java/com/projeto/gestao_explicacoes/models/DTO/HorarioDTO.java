package com.projeto.gestao_explicacoes.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HorarioDTO {

    private DayOfWeek diaSemana;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaInicio;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaFim;
    private String nomeExplicador;
}
