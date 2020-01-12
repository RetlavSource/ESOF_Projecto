package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Cadeira extends BaseModel{

  private String nome;
  private String sigla;

  @ManyToOne (cascade =  CascadeType.PERSIST)
  @ToString.Exclude
  //@JsonBackReference
  @JsonIgnore
  @EqualsAndHashCode.Exclude
  private Curso curso; // adicionado em "Curso"

  @ManyToMany (cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //@JsonBackReference
  @JsonIgnore
  private Set<Explicador> explicadores = new HashSet<>(); // adicionado em "Explicador"

  @OneToMany(mappedBy = "cadeira", cascade = CascadeType.PERSIST)
  @ToString.Exclude
  //@JsonManagedReference
  @JsonIgnore
  private Set<Atendimento> atendimentos = new HashSet<>();

  // ****** METHODS ******

  public Cadeira(String nome, String sigla) {
    this.nome = nome;
    this.sigla = sigla;
  }

  public void addAtendimento(Atendimento atendimento){
    this.atendimentos.add(atendimento);
    atendimento.setCadeira(this);
  }

  public void removeAtendimento(Atendimento atendimento) {
    this.atendimentos.remove(atendimento);
    atendimento.setCadeira(null);
  }

  @ToString.Include
  @JsonProperty
  public ArrayList<String> explicadores() {
    ArrayList<String> nomeExplicadores = new ArrayList<>();

    for (Explicador explicador : this.explicadores) {
      nomeExplicadores.add(explicador.getNome());
    }

    return nomeExplicadores;
  }
}