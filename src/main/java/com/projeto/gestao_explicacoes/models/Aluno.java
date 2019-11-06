package com.projeto.gestao_explicacoes.models;

import java.time.LocalDate;
import java.util.Set;

public class Aluno {

  private String nome;
  private LocalDate dataNascimento;

  private Curso curso;
  private Set<Atendimento> atendimentos;
}