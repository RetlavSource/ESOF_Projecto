package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private Integer numero;

  @ManyToOne
  private Curso curso; // adicionado em "Curso"

  @OneToMany(mappedBy = "aluno")
  private Set<Atendimento> atendimentos = new HashSet<>();

  // ****** METHODS ******

  public Aluno(String nome, Integer numero) {
    this.nome = nome;
    this.numero = numero;
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setAluno(this);
  }
}