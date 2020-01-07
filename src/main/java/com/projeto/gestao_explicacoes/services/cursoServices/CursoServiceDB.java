package com.projeto.gestao_explicacoes.services.cursoServices;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import com.projeto.gestao_explicacoes.repositories.CursoRepo;
import com.projeto.gestao_explicacoes.repositories.FaculdadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class CursoServiceDB implements CursoService {

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
    public Optional<Curso> criarCursoFaculdade(Curso curso, Faculdade faculdade) {

        if(this.faculdadeRepo.findByNome(faculdade.getNome()).isPresent()) {

            for (Faculdade faculdadeAux : this.faculdadeRepo.findAll()) {

                if (faculdadeAux.getNome().equals(faculdade.getNome())) {

                    if(this.cursoRepo.findByNome(curso.getNome()).isPresent()){

                        return Optional.empty();

                    }else{

                        faculdade.addCurso(curso);
                        cursoRepo.save(curso);
                        return Optional.of(curso);
                    }


                }

            }

        }

        return Optional.empty();

    }
}
