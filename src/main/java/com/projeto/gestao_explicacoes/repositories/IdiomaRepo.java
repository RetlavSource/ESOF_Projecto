package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Idioma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdiomaRepo extends CrudRepository<Idioma, Long> {

    Optional<Idioma> findByNome(String nome);

}
