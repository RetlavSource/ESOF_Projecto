package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaculdadeRepo extends CrudRepository<Faculdade, Long> {

    Optional<Faculdade> findByNome(String nome);
    Optional<Faculdade> findByCursos(Curso curso);

}
