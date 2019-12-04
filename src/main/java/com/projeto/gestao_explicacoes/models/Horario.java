package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Horario extends BaseModel{

  private DayOfWeek diaSemana;
  private LocalTime horaInicio;
  private LocalTime horaFim;

  @ManyToOne
  private Explicador explicador; // adicionado em "Explicador"

  // ****** METHODS ******

  public Horario(DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim) {
    this.diaSemana = diaSemana;
    this.horaInicio = horaInicio;
    this.horaFim = horaFim;
  }

  public Horario(DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim, Explicador explicador) {
    this.diaSemana = diaSemana;
    this.horaInicio = horaInicio;
    this.horaFim = horaFim;
    this.explicador = explicador;
  }
}