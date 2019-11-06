package com.projeto.gestaoexplicacoes.models;

import java.util.Set;

public class Cadeira {

  private String nome;
  private String lingua;

  private Curso curso;
  private Set<Explicador> explicadores;
  private Set<Atendimento> atendimentos;
}