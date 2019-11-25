package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadeiraRepo extends CrudRepository<Cadeira, Long> {

    Optional<Cadeira> findByNome(String nome);
    Optional<Cadeira> findByCurso(Curso curso);
    Optional<Cadeira> findByCurso_Nome(String nome);

}
