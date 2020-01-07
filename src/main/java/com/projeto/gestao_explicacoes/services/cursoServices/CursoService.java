package com.projeto.gestao_explicacoes.services.cursoServices;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;

import java.util.Optional;
import java.util.Set;

public interface CursoService {

    Set<Curso> findAll();

    Optional<Curso> criarCursoFaculdade(Curso curso, Faculdade faculdade);
}
