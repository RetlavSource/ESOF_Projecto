package com.projeto.gestao_explicacoes.services.cursoServices;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import com.projeto.gestao_explicacoes.repositories.CursoRepo;
import com.projeto.gestao_explicacoes.repositories.FaculdadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class CursoServiceDB implements CursoService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private CursoRepo cursoRepo;
    private FaculdadeRepo faculdadeRepo;

    @Autowired
    public CursoServiceDB(CursoRepo cursoRepo, FaculdadeRepo faculdadeRepo) {
        this.cursoRepo = cursoRepo;
        this.faculdadeRepo = faculdadeRepo;
    }

    @Override
    public Set<Curso> findAll() {
        Set<Curso> cursos = new HashSet<>();
        for (Curso curso: this.cursoRepo.findAll()) {
            cursos.add(curso);
        }
        return cursos;
    }

    //Curso tem que estar obrigatoriamente associada a uma faculdade na sua criacao
    @Override
    public Optional<Curso> criarCursoFaculdade(Curso curso, String nomeFaculdade) {
        this.logger.info("No método: CursoServiceDB -> criarCursoFaculdade");

        Optional<Faculdade> faculdadeOptional = this.faculdadeRepo.findByNome(nomeFaculdade);

        if (faculdadeOptional.isPresent()) {
            for (Curso cursoAux : faculdadeOptional.get().getCursos()) {
                if (cursoAux.getNome().equals(curso.getNome())) {
                    return Optional.empty();
                }
            }

            faculdadeOptional.get().addCurso(curso);
            this.cursoRepo.save(curso);
            return Optional.of(curso);
        }

        return Optional.empty();
    }
}
