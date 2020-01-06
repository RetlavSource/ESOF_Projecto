package com.projeto.gestao_explicacoes.services.faculdadeServices;

import com.projeto.gestao_explicacoes.models.Faculdade;

import java.util.Optional;
import java.util.Set;

public interface FaculdadeService {

    Set<Faculdade> findAll();

    Optional<Faculdade> findById(Long id);

    Optional<Faculdade> criarFaculdade(Faculdade faculdade);

}
