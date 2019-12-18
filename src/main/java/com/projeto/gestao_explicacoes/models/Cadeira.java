package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Cadeira extends BaseModel{

  private String nome;
  private String sigla;

  @ManyToOne
  @JsonBackReference
  private Curso curso; // adicionado em "Curso"

  @ManyToMany
  private Set<Explicador> explicadores = new HashSet<>(); // adicionado em "Explicador"

  @OneToMany(mappedBy = "cadeira")
  private Set<Atendimento> atendimentos = new HashSet<>();

  // ****** METHODS ******

  public Cadeira(String nome, String sigla) {
    this.nome = nome;
    this.sigla = sigla;
  }

  public Cadeira(String nome, String sigla, @NotNull Curso curso) {
    this(nome, sigla);
    this.curso = curso;
    curso.addCadeira(this);
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setCadeira(this);
  }
}