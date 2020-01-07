package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Explicador extends BaseModel{

  private String nome;
  private Integer numero;

  @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Horario> horarios = new HashSet<>();

  @ManyToMany(mappedBy = "explicadores" , cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Idioma> idiomas = new HashSet<>(); // tabela criada automaticamente

  @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Atendimento> atendimentos = new HashSet<>();

  @ManyToMany(mappedBy = "explicadores", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
  private Set<Cadeira> cadeiras = new HashSet<>();

  // ****** METHODS ******

  public Explicador(String nome){
    this.nome = nome;
  }

  public Explicador(String nome, Integer numero) {
    this.nome = nome;
    this.numero = numero;
  }

  public void addHorario(Horario horario){
    this.horarios.add(horario);
    horario.setExplicador(this);
  }

  public void removeHorario(Horario horario){
    this.horarios.remove(horario);
    horario.setExplicador(null);
  }

  public void addIdioma(Idioma idioma){
    this.idiomas.add(idioma);
    idioma.getExplicadores().add(this);
  }

  public void removeIdioma(Idioma idioma){
    this.idiomas.remove(idioma);
    idioma.getExplicadores().remove(this);
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setExplicador(this);
  }

  public void removeAtendimento(Atendimento atendimento){
    this.atendimentos.remove(atendimento);
    atendimento.setExplicador(null);
  }

  public void addCadeira(Cadeira cadeira) {
    this.cadeiras.add(cadeira);
    cadeira.getExplicadores().add(this);
  }

  public void removeCadeira(Cadeira cadeira) {
    this.cadeiras.remove(cadeira);
    cadeira.getExplicadores().remove(this);
  }
}