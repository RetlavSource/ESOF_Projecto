package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Cadeira {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String lingua;

  @ManyToOne
  private Curso curso; // adicionado em "Curso"

  @ManyToMany(mappedBy = "cadeiras")
  private Set<Explicador> explicadores = new HashSet<>();

  @OneToMany(mappedBy = "cadeira")
  private Set<Atendimento> atendimentos = new HashSet<>();

  // ****** METHODS ******

  public Cadeira(String nome, String lingua) {
    this.nome = nome;
    this.lingua = lingua;
  }

  public void addExplicador(Explicador explicador){
    this.explicadores.add(explicador);
    explicador.getCadeiras().add(this);
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setCadeira(this);
  }
}