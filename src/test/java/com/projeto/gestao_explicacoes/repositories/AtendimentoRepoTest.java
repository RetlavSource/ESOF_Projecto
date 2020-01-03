package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Idioma;
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
        Atendimento atendimento1=new Atendimento();
        atendimento1.setIdioma(new Idioma("português", "pt"));
        Atendimento atendimento2=new Atendimento();
        atendimento2.setIdioma(new Idioma("português-brasileiro", "pt-br"));


        Aluno aluno1=new Aluno("aluno1", 12345);
        atendimento1.setAluno(aluno1);

        Aluno aluno2=new Aluno("aluno2", 54321);
        atendimento2.setAluno(aluno2);

        Explicador explicador1=new Explicador("explicador1");
        Explicador explicador2=new Explicador("explicador2");
        atendimento1.setExplicador(explicador1);
        atendimento2.setExplicador(explicador2);

        atendimento1.setData(LocalDateTime.now());
        atendimento2.setData(LocalDateTime.now());

        atendimentoRepo.save(atendimento1);
        atendimentoRepo.save(atendimento2);

        assertTrue(atendimentoRepo.findByAlunoNumero(12345).isPresent());
        assertTrue(atendimentoRepo.findByAlunoNome("aluno1").isPresent());
        assertTrue(atendimentoRepo.findByAluno(aluno1).isPresent());
        assertTrue(atendimentoRepo.findByExplicadorAndIdiomaSigla(explicador1, "PT").isPresent());
        assertTrue(atendimentoRepo.findByExplicadorAndIdiomaSigla(explicador1, "pt".toUpperCase()).isPresent());

        assertTrue(atendimentoRepo.findByExplicadorOrIdiomaSigla(null, "PT").isPresent());
        assertTrue(atendimentoRepo.findByExplicadorOrIdiomaSigla(explicador1, null).isPresent());
        assertTrue(atendimentoRepo.findByExplicadorOrIdiomaSigla(null, null).isEmpty());

        assertTrue(atendimentoRepo.findByExplicadorAndIdiomaSigla(explicador1, "PT-BR").isEmpty());

    }
}