package com.projeto.gestao_explicacoes.services.cadeiraServices;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import com.projeto.gestao_explicacoes.repositories.CadeiraRepo;
import com.projeto.gestao_explicacoes.repositories.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class CadeiraServiceDB implements CadeiraService {

    private CadeiraRepo cadeiraRepo;
    private CursoRepo cursoRepo;

    @Autowired
    public CadeiraServiceDB(CadeiraRepo cadeiraRepo, CursoRepo cursoRepo) {

        this.cadeiraRepo = cadeiraRepo;
        this.cursoRepo = cursoRepo;
    }

    @Override
    public Set<Cadeira> findAll() {
        Set<Cadeira> cadeiras = new HashSet<>();
        for (Cadeira cadeira : this.cadeiraRepo.findAll()) {
            cadeiras.add(cadeira);
        }
        return cadeiras;
    }

    //Cadeira tem que estar obrigatoriamente associada a um curso na sua criacao
    @Override
    public Optional<Cadeira> criarCadeiraCurso(Cadeira cadeira, Curso curso) {

        if (this.cursoRepo.findByNome(curso.getNome()).isPresent()) {

            for (Curso cursoAux : this.cursoRepo.findAll()) {

                if (cursoAux.getNome().equals(curso.getNome())) {

                    if (this.cadeiraRepo.findByNome(cadeira.getNome()).isPresent()) {

                        return Optional.empty();

                    } else {

                        curso.addCadeira(cadeira);
                        cadeiraRepo.save(cadeira);
                        return Optional.of(cadeira);
                    }
                }

            }
        }
        return Optional.empty();
    }
}
