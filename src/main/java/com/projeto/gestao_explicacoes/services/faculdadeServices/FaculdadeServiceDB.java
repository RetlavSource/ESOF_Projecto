package com.projeto.gestao_explicacoes.services.faculdadeServices;

import com.projeto.gestao_explicacoes.models.Faculdade;
import com.projeto.gestao_explicacoes.repositories.FaculdadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class FaculdadeServiceDB implements FaculdadeService {

    private FaculdadeRepo faculdadeRepo;

    @Autowired
    public FaculdadeServiceDB(FaculdadeRepo faculdadeRepo) {

        this.faculdadeRepo = faculdadeRepo;
    }

    @Override
    public Set<Faculdade> findAll() {
        Set<Faculdade> faculdades = new HashSet<>();
        for (Faculdade faculdade : this.faculdadeRepo.findAll()) {
            faculdades.add(faculdade);
        }
        return faculdades;
    }

    @Override
    public Optional<Faculdade> findById(Long id) {

        return this.faculdadeRepo.findById(id);
    }

    @Override
    public Optional<Faculdade> criarFaculdade(Faculdade faculdade) {

        if(this.faculdadeRepo.findByNome(faculdade.getNome()).isPresent()){

            return Optional.empty();
        }

        Faculdade faculdadeCriada = this.faculdadeRepo.save(faculdade);

        return Optional.of(faculdadeCriada);
    }

}
