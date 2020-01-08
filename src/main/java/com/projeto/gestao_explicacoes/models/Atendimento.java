package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
public class Atendimento extends BaseModel{

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime data;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //@JsonBackReference
  @JsonIgnore
  private Explicador explicador; // adicionado em "Explicador"

  @ManyToOne(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //@JsonBackReference
  @JsonIgnore
  private Aluno aluno; // adicionado em "Aluno"

  @ManyToOne(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //@JsonBackReference
  @JsonIgnore
  private Cadeira cadeira; // adicionado em "Cadeira"

  @ManyToOne(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //@JsonBackReference
  @JsonIgnore
  private Idioma idioma; // adicionado em "Idioma"

  // ****** METHODS ******


  public Atendimento() {
    this.data = LocalDateTime.now();
  }

  public Atendimento(LocalDateTime data) {
    this.data = data;
  }

  public Atendimento(Explicador explicador, Aluno aluno, Cadeira cadeira, Idioma idioma) {
    this();
    this.explicador = explicador;
    this.aluno = aluno;
    this.cadeira = cadeira;
    this.idioma = idioma;
  }

  @ToString.Include
  @JsonProperty
  public String explicador() {
    return explicador.getNome();
  }

  @ToString.Include
  @JsonProperty
  public String aluno() {
    return aluno.getNome();
  }

  @ToString.Include
  @JsonProperty
  public String cadeira() {
    return cadeira.getNome();
  }

  @ToString.Include
  @JsonProperty
  public String idioma() {
    return idioma.getNome();
  }
}
