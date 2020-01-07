package com.projeto.gestao_explicacoes.services.idiomaServices;

import com.projeto.gestao_explicacoes.models.Idioma;
import com.projeto.gestao_explicacoes.repositories.IdiomaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "db")
public class IdiomaServiceDB implements IdiomaService {

    private IdiomaRepo idiomaRepo;

    @Autowired
    public IdiomaServiceDB(IdiomaRepo idiomaRepo) {
        this.idiomaRepo = idiomaRepo;
    }

    @Override
    public Set<Idioma> findAll() {
        Set<Idioma> idiomas = new HashSet<>();
        for (Idioma idioma : this.idiomaRepo.findAll()) {
            idiomas.add(idioma);
        }
        return idiomas;
    }
}
