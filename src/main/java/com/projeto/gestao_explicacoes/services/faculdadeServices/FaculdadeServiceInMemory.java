package com.projeto.gestao_explicacoes.services.faculdadeServices;

import com.projeto.gestao_explicacoes.models.Faculdade;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "inmemory")
public class FaculdadeServiceInMemory implements FaculdadeService {

    @Override
    public Set<Faculdade> findAll() {
        return null;
    }

    @Override
    public Optional<Faculdade> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Faculdade> criarFaculdade(Faculdade faculdade) {

        return Optional.empty();
    }
}
