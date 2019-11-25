package com.projeto.gestao_explicacoes;

import com.projeto.gestao_explicacoes.repositories.AlunoRepo;
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

    }
}
