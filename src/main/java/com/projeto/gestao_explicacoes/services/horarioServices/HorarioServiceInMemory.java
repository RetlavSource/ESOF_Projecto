package com.projeto.gestao_explicacoes.services.horarioServices;

import com.projeto.gestao_explicacoes.models.Horario;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile(value = "inmemory")
public class HorarioServiceInMemory implements HorarioService {
    @Override
    public Set<Horario> findAll() {
        return null;
    }
}
