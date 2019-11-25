package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Faculdade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @OneToMany(mappedBy = "faculdade")
  private Set<Curso> cursos = new HashSet<>();

  // ****** METHODS ******

  public Faculdade(String nome) {
    this.nome = nome;
  }

  public void addCurso(Curso curso){
    this.cursos.add(curso);
    curso.setFaculdade(this);
  }
}