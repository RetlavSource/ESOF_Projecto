package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Idioma {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome; // Nome do idioma em UpperCase
  private String sigla; // Sigla em UpperCase

  // ****** METHODS ******

  public Idioma(String nome, String sigla) {
    this.nome = nome.toUpperCase();
    this.sigla = sigla.toUpperCase();
  }
}