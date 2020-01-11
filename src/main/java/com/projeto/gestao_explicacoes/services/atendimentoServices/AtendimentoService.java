package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.services.atendimentoServices.filters.AtendimentoObject;

import java.util.Optional;
import java.util.Set;

public interface AtendimentoService {

    Set<Atendimento> findAll();

    Optional<AtendimentoObject> criarAtendimento(AtendimentoObject objAtendimento);
}
