package com.projeto.gestao_explicacoes;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Curso;
import com.projeto.gestao_explicacoes.models.Faculdade;
import com.projeto.gestao_explicacoes.repositories.CadeiraRepo;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private FaculdadeRepo faculdadeRepo;
    private CursoRepo cursoRepo;
    private CadeiraRepo cadeiraRepo;

    @Autowired
    public Bootstrap(FaculdadeRepo faculdadeRepo, CursoRepo cursoRepo, CadeiraRepo cadeiraRepo) {
        this.faculdadeRepo = faculdadeRepo;
        this.cursoRepo = cursoRepo;
        this.cadeiraRepo = cadeiraRepo;
    }

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

        Curso engInformatica = new Curso("Engenharia Informática");
        faculdade1.addCurso(engInformatica);

        Curso engCivil = new Curso("Engenharia Civil");
        faculdade1.addCurso(engCivil);

        Curso psicologia = new Curso("Psicologia");
        faculdade2.addCurso(psicologia);

        Curso fisioterapia = new Curso("Fisioterapia");
        faculdade3.addCurso(fisioterapia);

        this.cursoRepo.save(engInformatica);
        this.cursoRepo.save(engCivil);
        this.cursoRepo.save(psicologia);
        this.cursoRepo.save(fisioterapia);

        Cadeira alg1 = new Cadeira("Algoritmos e estruturas de Dados I", "ALG1");
        engInformatica.addCadeira(alg1);



    }
}
