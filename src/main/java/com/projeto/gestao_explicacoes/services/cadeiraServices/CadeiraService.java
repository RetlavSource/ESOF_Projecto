package com.projeto.gestao_explicacoes.services.cadeiraServices;

import com.projeto.gestao_explicacoes.models.Cadeira;

import java.util.Optional;
import java.util.Set;

public interface CadeiraService {

    Set<Cadeira> findAll();

    Optional<Cadeira> criarCadeiraCurso(Cadeira cadeira, String curso);

}
