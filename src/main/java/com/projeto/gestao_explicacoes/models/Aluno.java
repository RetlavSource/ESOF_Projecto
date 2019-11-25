package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
  private LocalDate dataNascimento;

  @ManyToOne
  private Curso curso; // adicionado em "Curso"

  @OneToMany(mappedBy = "aluno")
  private Set<Atendimento> atendimentos = new HashSet<>();

  // ****** METHODS ******

  public Aluno(String nome, Integer numero, LocalDate dataNascimento) {
    this.nome = nome;
    this.numero = numero;
    this.dataNascimento = dataNascimento;
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setAluno(this);
  }
}