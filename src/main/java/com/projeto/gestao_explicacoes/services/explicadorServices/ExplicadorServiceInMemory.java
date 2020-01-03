package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile(value = "inmemory")
public class ExplicadorServiceInMemory implements ExplicadorService {
    @Override
    public Set<Explicador> findAll() {
        return null;
    }
}
