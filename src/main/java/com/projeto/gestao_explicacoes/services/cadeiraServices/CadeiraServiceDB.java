package com.projeto.gestao_explicacoes.services.cadeiraServices;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.repositories.CadeiraRepo;
import com.projeto.gestao_explicacoes.repositories.CursoRepo;
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
public class CadeiraServiceDB implements CadeiraService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

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
    public Optional<Cadeira> criarCadeiraCurso(Cadeira cadeira, String nomeCurso) {
        this.logger.info("No método: CadeiraServiceDB -> criarCadeiraCurso");

        Optional<Curso> cursoOptional = this.cursoRepo.findByNome(nomeCurso);

        if (cursoOptional.isPresent()) {
            for (Cadeira cadeiraAux : cursoOptional.get().getCadeiras()) {
                if (cadeiraAux.getNome().equals(cadeira.getNome())) {
                    return Optional.empty();
                }
            }

            cursoOptional.get().addCadeira(cadeira);
            this.cadeiraRepo.save(cadeira);
            return Optional.of(cadeira);
        }

        return Optional.empty();
    }
}
