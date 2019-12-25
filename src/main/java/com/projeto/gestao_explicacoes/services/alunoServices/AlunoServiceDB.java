package com.projeto.gestao_explicacoes.services.alunoServices;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.repositories.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class AlunoServiceDB implements AlunoService {

    private AlunoRepo alunoRepo;

    @Autowired
    public AlunoServiceDB(AlunoRepo alunoRepo) {
        this.alunoRepo = alunoRepo;
    }

    @Override
    public Set<Aluno> findAll() {
        Set<Aluno> alunos = new HashSet<>();
        for (Aluno aluno : this.alunoRepo.findAll()) {
            alunos.add(aluno);
        }
        return alunos;
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        return this.alunoRepo.findById(id);
    }

    @Override
    public Optional<Aluno> criarAluno(Aluno aluno) {
        if (this.alunoRepo.findByNumero(aluno.getNumero()).isPresent()) {
            return Optional.empty();
        }
        Aluno alunoCriado = this.alunoRepo.save(aluno);
        return Optional.of(alunoCriado);
    }
}
