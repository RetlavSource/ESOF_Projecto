package com.projeto.gestao_explicacoes;

import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import com.projeto.gestao_explicacoes.repositories.AlunoRepo;
import com.projeto.gestao_explicacoes.repositories.CursoRepo;
import com.projeto.gestao_explicacoes.repositories.FaculdadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.*;


@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AlunoRepo alunoRepo; // enunciar os repositorios a utilizar

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    @Autowired
    private CursoRepo cursoRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

        //exemploEntradas();
        novosDados();
    }

    private void exemploEntradas(){
        // Dados para introdução de Datas
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.of(2012, Month.SEPTEMBER, 21));
        System.out.println(LocalTime.now());
        System.out.println(LocalTime.of(20, 36, 56));
        System.out.println(LocalDateTime.now());
        System.out.println(DayOfWeek.MONDAY);

        // Para introdução de Idiomas (Idioma em UpperCase)
        String idioma = "português";
        System.out.println(idioma.toUpperCase());
    }

    private void novosDados(){

        Faculdade faculdade1 = new Faculdade("Ciências e Tecnologia");
        Faculdade faculdade2 = new Faculdade("Ciências Humanas e Sociais");
        Faculdade faculdade3 = new Faculdade("Ciências da Saúde");

        this.faculdadeRepo.save(faculdade1);
        this.faculdadeRepo.save(faculdade2);
        this.faculdadeRepo.save(faculdade3);

        Curso engInfor = new Curso("Engenharia Informática");
        Curso engCivil = new Curso("Engenharia Civil");
        Curso arq = new Curso("Arquitectura");
        Curso psico = new Curso("Psicologia");
        Curso fisio = new Curso("Fisioterapia");

        faculdade1.addCurso(engInfor);
        faculdade1.addCurso(engCivil);
        faculdade1.addCurso(arq);
        faculdade2.addCurso(psico);
        faculdade3.addCurso(fisio);

        this.cursoRepo.save(engInfor);
        this.cursoRepo.save(engCivil);
        this.cursoRepo.save(arq);
        this.cursoRepo.save(psico);
        this.cursoRepo.save(fisio);




    }
}
