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
  private Curso curso;
  @ManyToMany
  private Set<Explicador> explicadores = new HashSet<>();
  @OneToMany
  private Set<Atendimento> atendimentos = new HashSet<>();
}