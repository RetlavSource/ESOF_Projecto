package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Explicador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtendimentoRepo extends CrudRepository<Atendimento, Long> {

    Optional<Atendimento> findByAluno(Aluno aluno);
    Optional<Atendimento> findByAlunoNome(String nome);
    Optional<Atendimento> findByAlunoNumero(Integer numero);
    Optional<Atendimento> findByExplicadorAndIdiomaSigla(Explicador explicador, String sigla);
    Optional<Atendimento> findByExplicadorOrIdiomaSigla(Explicador explicador, String sigla);

}
