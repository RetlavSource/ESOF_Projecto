package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.DTO.AtendimentoDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "inmemory")
public class AtendimentoServiceInMemory implements AtendimentoService {

    @Override
    public Set<Atendimento> findAll() {
        return null;
    }

    @Override
    public Optional<AtendimentoDTO> criarAtendimento(AtendimentoDTO objAtendimento) {

        return Optional.empty();
    }
}
