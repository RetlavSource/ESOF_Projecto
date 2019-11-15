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
public class Explicador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private LocalDate dataNascimento;

  @OneToMany
  private Set<Horario> horarios = new HashSet<>();
  @OneToMany
  private Set<Idioma> idiomas = new HashSet<>();
  @OneToMany
  private Set<Atendimento> atendimentos = new HashSet<>();
  @ManyToMany
  private Set<Cadeira> cadeiras = new HashSet<>();
}