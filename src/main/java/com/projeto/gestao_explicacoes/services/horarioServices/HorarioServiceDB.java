package com.projeto.gestao_explicacoes.services.horarioServices;

import com.projeto.gestao_explicacoes.models.Horario;
import com.projeto.gestao_explicacoes.repositories.HorarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "db")
public class HorarioServiceDB implements HorarioService {

    private HorarioRepo horarioRepo;

    @Autowired
    public HorarioServiceDB(HorarioRepo horarioRepo) {
        this.horarioRepo = horarioRepo;
    }

    @Override
    public Set<Horario> findAll() {
        Set<Horario> horarios = new HashSet<>();
        for (Horario horario : this.horarioRepo.findAll()) {
            horarios.add(horario);
        }
        return horarios;
    }
}
