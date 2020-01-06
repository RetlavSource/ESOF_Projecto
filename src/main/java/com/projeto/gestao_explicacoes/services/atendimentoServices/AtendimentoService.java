package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Atendimento;

import java.util.Optional;

public interface AtendimentoService{

    Optional<Atendimento> criarAtendimento(Atendimento atendimento);
}
