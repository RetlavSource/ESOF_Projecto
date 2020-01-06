package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Atendimento;

import java.util.Optional;

public class AtendimentoServiceInMemory implements AtendimentoService{

    @Override
    public Optional<Atendimento> criarAtendimento(Atendimento atendimento) {

        return Optional.empty();
    }

}
