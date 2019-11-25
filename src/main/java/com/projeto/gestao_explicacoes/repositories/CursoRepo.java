package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepo extends CrudRepository<Curso, Long> {

    Optional<Curso> findByNome(String nome);
    Optional<Curso> findByFaculdade(Faculdade faculdade);
    Optional<Curso> findByFaculdade_Nome(String nome);

}
