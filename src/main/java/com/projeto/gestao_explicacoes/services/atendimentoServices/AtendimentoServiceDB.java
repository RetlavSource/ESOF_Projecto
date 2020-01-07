package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.repositories.AtendimentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "db")
public class AtendimentoServiceDB implements AtendimentoService {

    private AtendimentoRepo atendimentoRepo;

    @Autowired
    public AtendimentoServiceDB(AtendimentoRepo atendimentoRepo) {
        this.atendimentoRepo = atendimentoRepo;
    }

    @Override
    public Set<Atendimento> findAll() {
        Set<Atendimento> atendimentos = new HashSet<>();
        for (Atendimento atendimento: this.atendimentoRepo.findAll()) {
            atendimentos.add(atendimento);
        }
        return atendimentos;
    }
}
