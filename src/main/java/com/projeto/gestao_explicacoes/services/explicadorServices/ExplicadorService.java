package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.ExplicadorDTO;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ExplicadorService {

    Set<Explicador> findAll();

    Optional<Explicador> criarExplicador(Explicador explicador);

    Optional<ExplicadorDTO> findByNome(String nomeExplicador);

    Optional<ExplicadorDTO> modificaExplicador(ExplicadorDTO infoExplicador);

    Set<ExplicadorDTO> procuraExplicadores(Map<String, String> parametros);
}
