package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

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
  private Faculdade faculdade;

  @OneToMany(mappedBy = "curso")
  private Set<Cadeira> cadeiras = new HashSet<>();

  @OneToMany(mappedBy = "curso")
  private Set<Aluno>  alunos = new HashSet<>();
}