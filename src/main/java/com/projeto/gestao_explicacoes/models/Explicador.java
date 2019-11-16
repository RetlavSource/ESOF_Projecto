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
  @JoinTable(name = "explicador_horario", joinColumns = @JoinColumn(name = "explicador_id"), inverseJoinColumns = @JoinColumn(name = "horario_id"))
  private Set<Horario> horarios = new HashSet<>();

  @OneToMany
  @JoinTable(name = "explicador_idioma", joinColumns = @JoinColumn(name = "explicador_id"), inverseJoinColumns = @JoinColumn(name = "idioma_id"))
  private Set<Idioma> idiomas = new HashSet<>();

  @OneToMany(mappedBy = "explicador")
  private Set<Atendimento> atendimentos = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "explicador_cadeira", joinColumns = @JoinColumn(name = "explicador_id"), inverseJoinColumns = @JoinColumn(name = "cadeira_id"))
  private Set<Cadeira> cadeiras = new HashSet<>();
}