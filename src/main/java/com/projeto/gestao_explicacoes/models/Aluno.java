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
  private LocalDate dataNascimento;

  @ManyToOne
  private Curso curso;

  @OneToMany(mappedBy = "aluno")
  private Set<Atendimento> atendimentos = new HashSet<>();
}