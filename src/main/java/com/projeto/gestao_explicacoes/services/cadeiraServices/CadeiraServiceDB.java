package com.projeto.gestao_explicacoes.services.cadeiraServices;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.repositories.CadeiraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "db")
public class CadeiraServiceDB implements CadeiraService {

    private CadeiraRepo cadeiraRepo;

    @Autowired
    public CadeiraServiceDB(CadeiraRepo cadeiraRepo) {
        this.cadeiraRepo = cadeiraRepo;
    }

    @Override
    public Set<Cadeira> findAll() {
        Set<Cadeira> cadeiras = new HashSet<>();
        for (Cadeira cadeira : this.cadeiraRepo.findAll()) {
            cadeiras.add(cadeira);
        }
        return cadeiras;
    }
}
