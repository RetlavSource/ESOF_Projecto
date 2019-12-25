package com.projeto.gestao_explicacoes.services.cursoServices;

import com.projeto.gestao_explicacoes.models.Curso;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile(value = "inmemory")
public class CursoServiceInMemory implements CursoService {

    @Override
    public Set<Curso> findAll() {
        return null;
    }
}
