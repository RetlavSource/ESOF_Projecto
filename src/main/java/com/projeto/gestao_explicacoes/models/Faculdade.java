package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Faculdade extends BaseModel{

  private String nome;

  @OneToMany(mappedBy = "faculdade", cascade = CascadeType.PERSIST)
  //@JsonManagedReference //Dá erro com esta anotação quando fazemos um POST
  @JsonIgnore
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