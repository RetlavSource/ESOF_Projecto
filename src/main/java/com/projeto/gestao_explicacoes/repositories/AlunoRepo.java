package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepo extends CrudRepository<Aluno,Long> {

    Optional<Aluno> findByNome(String nome);
    Optional<Aluno> findByNumero(Integer numero);

}
