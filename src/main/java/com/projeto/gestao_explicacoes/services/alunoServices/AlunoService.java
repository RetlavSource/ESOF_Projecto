package com.projeto.gestao_explicacoes.services.alunoServices;

import com.projeto.gestao_explicacoes.models.Aluno;

import java.util.Optional;
import java.util.Set;

public interface AlunoService {

    Set<Aluno> findAll();

    Optional<Aluno> findById(Long id);

    Optional<Aluno> criarAluno(Aluno aluno);
}
