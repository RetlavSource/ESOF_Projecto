package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
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
    public Optional<ExplicadorDTO> criarExplicador(ExplicadorDTO explicadorDTO) {
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

    @Override
    public Set<ExplicadorDTO> procuraExplicadores(Map<String, String> parametros) {
        return null;
    }

    @Override
    public Optional<ExplicadorDTO> adicionaCadeiraAoExplicador(ExplicadorDTO infoExplicador, String nomeCadeira) {
        return Optional.empty();
    }
}
