package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Idioma extends BaseModel{

  private String nome; // Nome do idioma em UpperCase
  private String sigla; // Sigla em UpperCase

  // ****** METHODS ******

  public Idioma(String nome, String sigla) {
    this.nome = nome.toUpperCase();
    this.sigla = sigla.toUpperCase();
  }
}