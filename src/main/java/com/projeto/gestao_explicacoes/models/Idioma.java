package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Idioma extends BaseModel{

  private String nome; // Nome do idioma em UpperCase
  private String sigla; // Sigla em UpperCase

  @OneToMany(mappedBy = "idioma", cascade = CascadeType.PERSIST)
  @JsonManagedReference
  private Set<Atendimento> atendimentos = new HashSet<>();

  @ManyToMany(cascade = CascadeType.PERSIST)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference
  private Set<Explicador> explicadores = new HashSet<>();

  // ****** METHODS ******

  public Idioma(String nome, String sigla) {
    this.nome = nome.toUpperCase();
    this.sigla = sigla.toUpperCase();
  }

  public void addAtendimento(Atendimento atendimento) {
    this.atendimentos.add(atendimento);
    atendimento.setIdioma(this);
  }

  public void removeAtendimento(Atendimento atendimento) {
    this.atendimentos.remove(atendimento);
    atendimento.setIdioma(null);
  }
}