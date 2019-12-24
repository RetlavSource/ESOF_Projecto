package com.projeto.gestao_explicacoes.services.cursoServices;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.repositories.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "db")
public class CursoServiceDB implements CursoService {

    private CursoRepo cursoRepo;

    @Autowired
    public CursoServiceDB(CursoRepo cursoRepo) {
        this.cursoRepo = cursoRepo;
    }

    @Override
    public Set<Curso> findAll() {
        Set<Curso> cursos = new HashSet<>();
        for (Curso curso: this.cursoRepo.findAll()) {
            cursos.add(curso);
        }
        return cursos;
    }
}
