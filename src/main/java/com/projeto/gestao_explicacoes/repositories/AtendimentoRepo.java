package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.models.Atendimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtendimentoRepo extends CrudRepository<Atendimento, Long> {

    Optional<Atendimento> findByAluno(Aluno aluno);
    Optional<Atendimento> findByAlunoNome(String nome);
    Optional<Atendimento> findByAlunoNumero(Integer numero);

}
