package com.projeto.gestao_explicacoes.services.cadeiraServices;

import com.projeto.gestao_explicacoes.models.Cadeira;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "inmemory")
public class CadeiraServiceInMemory implements CadeiraService {

    @Override
    public Set<Cadeira> findAll() {
        return null;
    }

    @Override
    public Optional<Cadeira> criarCadeiraCurso(Cadeira cadeira, String curso) {
        return Optional.empty();
    }
}
