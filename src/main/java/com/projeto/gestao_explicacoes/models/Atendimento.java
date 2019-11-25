package com.projeto.gestao_explicacoes.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Atendimento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime data;

  @ManyToOne
  private Explicador explicador; // adicionado em "Explicador"

  @ManyToOne
  private Aluno aluno; // adicionado em "Aluno"

  @ManyToOne
  private Cadeira cadeira; // adicionado em "Cadeira"

  // ****** METHODS ******


  public Atendimento() {
    this.data = LocalDateTime.now();
  }

  public Atendimento(Explicador explicador, Aluno aluno, Cadeira cadeira) {
    this();
    this.explicador = explicador;
    this.aluno = aluno;
    this.cadeira = cadeira;
  }
}