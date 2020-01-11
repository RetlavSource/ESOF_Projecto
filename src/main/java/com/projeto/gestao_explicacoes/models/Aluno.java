package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Aluno extends BaseModel{

  private String nome;
  private Integer numero;

  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //@JsonBackReference
  @JsonIgnore
  private Curso curso; // adicionado em "Curso"

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.PERSIST)
  //@JsonManagedReference
  @JsonIgnore
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

  public void removeAtendimento(Atendimento atendimento) {
    this.atendimentos.remove(atendimento);
    atendimento.setAluno(null);
  }
}
