package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.repositories.ExplicadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class ExplicadorServiceDB implements ExplicadorService {

    private ExplicadorRepo explicadorRepo;

    @Autowired
    public ExplicadorServiceDB(ExplicadorRepo explicadorRepo) {
        this.explicadorRepo = explicadorRepo;
    }

    @Override
    public Set<Explicador> findAll() {
        Set<Explicador> explicadores = new HashSet<>();
        for (Explicador explicador : this.explicadorRepo.findAll()) {
            explicadores.add(explicador);
        }
        return explicadores;
    }

    @Override
    public Optional<Explicador> criarExplicador(Explicador explicador) {

        if(this.explicadorRepo.findByNumero(explicador.getNumero()).isPresent()){

            return Optional.empty();
        }

        Explicador explicadorCriado = this.explicadorRepo.save(explicador);

        return Optional.of(explicadorCriado);
    }

}
