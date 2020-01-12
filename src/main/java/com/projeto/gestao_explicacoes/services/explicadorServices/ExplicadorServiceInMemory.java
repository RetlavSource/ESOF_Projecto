package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.ExplicadorDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "inmemory")
public class ExplicadorServiceInMemory implements ExplicadorService {
    @Override
    public Set<Explicador> findAll() {
        return null;
    }

    @Override
    public Optional<Explicador> criarExplicador(Explicador explicador) {

        return Optional.empty();
    }

    @Override
    public Optional<ExplicadorDTO> findByNome(String nomeExplicador) {
        return Optional.empty();
    }

    @Override
    public Optional<ExplicadorDTO> modificaExplicador(ExplicadorDTO infoExplicador) {
        return Optional.empty();
    }
}
