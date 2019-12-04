package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Atendimento extends BaseModel{

  private LocalDateTime data;

  @ManyToOne
  private Explicador explicador; // adicionado em "Explicador"

  @ManyToOne
  private Aluno aluno; // adicionado em "Aluno"

  @ManyToOne
  private Cadeira cadeira; // adicionado em "Cadeira"

  @OneToOne
  private Idioma idioma; // ligação unidireccional

  // ****** METHODS ******


  public Atendimento() {
    this.data = LocalDateTime.now();
  }

  public Atendimento(Explicador explicador, Aluno aluno, Cadeira cadeira, Idioma idioma) {
    this();
    this.explicador = explicador;
    this.aluno = aluno;
    this.cadeira = cadeira;
    this.idioma = idioma;
  }
}