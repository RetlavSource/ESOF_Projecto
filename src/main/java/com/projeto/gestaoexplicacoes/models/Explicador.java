package com.projeto.gestaoexplicacoes.models;

import java.time.LocalDate;
import java.util.Set;

public class Explicador {

  private String nome;
  private LocalDate dataNascimento;

  private Set<Horario> horarios;
  private Set<Atendimento> atendimentos;
  private Set<Cadeira> cadeiras;
  private Set<Idioma> idiomas;
}