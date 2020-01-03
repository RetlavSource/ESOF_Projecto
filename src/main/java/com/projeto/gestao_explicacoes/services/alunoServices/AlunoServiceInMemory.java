package com.projeto.gestao_explicacoes.services.alunoServices;

import com.projeto.gestao_explicacoes.models.Aluno;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "inmemory")
public class AlunoServiceInMemory implements AlunoService {

    @Override
    public Set<Aluno> findAll() {
        return null;
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Aluno> criarAluno(Aluno aluno) {
        return Optional.empty();
    }
}
