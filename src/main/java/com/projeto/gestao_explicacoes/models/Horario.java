package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
public class Horario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
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