package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
public class Atendimento extends BaseModel{

  private LocalDateTime data;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference
  private Explicador explicador; // adicionado em "Explicador"

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Aluno aluno; // adicionado em "Aluno"

  @ManyToOne(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference
  private Cadeira cadeira; // adicionado em "Cadeira"

  @OneToOne(cascade = CascadeType.PERSIST)
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