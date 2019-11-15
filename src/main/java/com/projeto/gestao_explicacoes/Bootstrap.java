package com.projeto.gestao_explicacoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    //@Autowired
    //private AlunoRepo alunoRepo; // enunciar os repositorios a utilizar

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("Startup");


        //Aluno aluno = new Aluno("Aluno1");
        //aluno.setId(1L);
        //this.alunoRepo.save(aluno);


    }
}
