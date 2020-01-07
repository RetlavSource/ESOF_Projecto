package com.projeto.gestao_explicacoes.services.idiomaServices;

import com.projeto.gestao_explicacoes.models.Idioma;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile(value = "inmemory")
public class IdiomaServiceInMemory implements IdiomaService {

    @Override
    public Set<Idioma> findAll() {
        return null;
    }
}
