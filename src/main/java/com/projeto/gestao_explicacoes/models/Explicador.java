package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Explicador extends BaseModel{

  private String nome;
  private LocalDate dataNascimento;

  @OneToMany(mappedBy = "explicador")
  private Set<Horario> horario = new HashSet<>();

  @OneToMany
  private Set<Idioma> idiomas = new HashSet<>(); // tabela criada automaticamente

  @OneToMany(mappedBy = "explicador")
  private Set<Atendimento> atendimentos = new HashSet<>();

  @ManyToMany(mappedBy = "explicadores")
  private Set<Cadeira> cadeiras = new HashSet<>();

  // ****** METHODS ******

  public Explicador(String nome, LocalDate dataNascimento) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
  }

  public void addHorario(Horario horario){
    this.horario.add(horario);
    horario.setExplicador(this);
  }

  public void addIdioma(Idioma idioma){
    this.idiomas.add(idioma);
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setExplicador(this);
  }

  public void addCadeira(Cadeira cadeira) {
    this.cadeiras.add(cadeira);
    cadeira.getExplicadores().add(this);
  }
}