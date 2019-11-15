package com.projeto.gestao_explicacoes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Atendimento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime data;

  @ManyToOne
  private Explicador explicador;
  @ManyToOne
  private Aluno aluno;
  @ManyToOne
  private Cadeira cadeira;
}