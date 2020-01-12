package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.services.atendimentoServices.filters.AtendimentoDTO;

import java.util.Optional;
import java.util.Set;

public interface AtendimentoService {

    Set<Atendimento> findAll();

    Optional<AtendimentoDTO> criarAtendimento(AtendimentoDTO objAtendimento);
}
