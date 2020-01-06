package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Idioma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExplicadorRepo extends CrudRepository<Explicador, Long> {

    Optional<Explicador> findByNome(String nome);
    Optional<Explicador> findByNumero(Integer numero);
    Optional<Explicador> findByCadeiras(Cadeira cadeira);
    Optional<Explicador> findByIdiomas(Idioma idioma);

}
