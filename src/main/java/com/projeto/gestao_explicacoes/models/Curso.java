package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Curso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Faculdade faculdade; // adicionado em "Faculdade"

  @OneToMany(mappedBy = "curso")
  private Set<Cadeira> cadeiras = new HashSet<>();

  @OneToMany(mappedBy = "curso")
  private Set<Aluno>  alunos = new HashSet<>();

  // ****** METHODS ******

  public Curso(String nome) {
    this.nome = nome;
  }

  public void addCadeira(Cadeira cadeira){
    this.cadeiras.add(cadeira);
    cadeira.setCurso(this);
  }

  public void addAluno(Aluno aluno){
    this.alunos.add(aluno);
    aluno.setCurso(this);
  }

}