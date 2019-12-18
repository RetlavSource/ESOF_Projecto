package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Curso extends BaseModel{

  private String nome;

  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference
  private Faculdade faculdade; // adicionado em "Faculdade"

  @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
  @JsonManagedReference
  private Set<Cadeira> cadeiras = new HashSet<>();

  @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
  @JsonManagedReference
  private Set<Aluno>  alunos = new HashSet<>();

  // ****** METHODS ******

  public Curso(String nome) {
    this.nome = nome;
  }

  public Curso(String nome, @NotNull Faculdade faculdade){
    this(nome);
    this.faculdade = faculdade;
    faculdade.addCurso(this);
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