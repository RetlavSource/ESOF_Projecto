package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Explicador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AtendimentoRepoTest {


    @Autowired
    private AtendimentoRepo atendimentoRepo;

    @Test
    public void test(){
        Atendimento atendimento=new Atendimento();
        Aluno aluno=new Aluno();
        aluno.setNome("aluno1");
        aluno.setNumero(12345);
        atendimento.setAluno(aluno);

        Explicador explicador=new Explicador();
        explicador.setNome("explicador1");

        atendimento.setData(LocalDateTime.now());

        atendimentoRepo.save(atendimento);

        assertTrue(atendimentoRepo.findByAlunoNumero(12345).isPresent());
        assertTrue(atendimentoRepo.findByAlunoNome("aluno1").isPresent());

    }
}