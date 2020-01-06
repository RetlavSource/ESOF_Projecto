package com.projeto.gestao_explicacoes;

import com.projeto.gestao_explicacoes.models.*;
import com.projeto.gestao_explicacoes.models.builders.AtendimentoBuilder;
import com.projeto.gestao_explicacoes.models.builders.ExplicadorBuilder;
import com.projeto.gestao_explicacoes.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private FaculdadeRepo faculdadeRepo;
    private CursoRepo cursoRepo;
    private CadeiraRepo cadeiraRepo;
    private AlunoRepo alunoRepo;
    private ExplicadorRepo explicadorRepo;
    private IdiomaRepo idiomaRepo;
    private HorarioRepo horarioRepo;
    private AtendimentoRepo atendimentoRepo;

    @Autowired
    public Bootstrap(FaculdadeRepo faculdadeRepo, CursoRepo cursoRepo, CadeiraRepo cadeiraRepo, AlunoRepo alunoRepo, ExplicadorRepo explicadorRepo, IdiomaRepo idiomaRepo, HorarioRepo horarioRepo, AtendimentoRepo atendimentoRepo) {
        this.faculdadeRepo = faculdadeRepo;
        this.cursoRepo = cursoRepo;
        this.cadeiraRepo = cadeiraRepo;
        this.alunoRepo = alunoRepo;
        this.explicadorRepo = explicadorRepo;
        this.idiomaRepo = idiomaRepo;
        this.horarioRepo = horarioRepo;
        this.atendimentoRepo = atendimentoRepo;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

        //exemploEntradas();
        novosDados();
    }

    private void exemploEntradas(){
        // Dados para introdução de Datas

        LocalDateTime dateAndTime = null;
        dateAndTime = LocalDateTime.parse("2018-11-11 23:23", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println(dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println(dateAndTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        System.out.println(LocalDateTime.parse("2019-12-21T12:12").format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
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


        Curso engInformatica = new Curso("Engenharia Informática");
        faculdade1.addCurso(engInformatica);

        Curso engCivil = new Curso("Engenharia Civil");
        faculdade1.addCurso(engCivil);

        Curso psicologia = new Curso("Psicologia");
        faculdade2.addCurso(psicologia);

        Curso fisioterapia = new Curso("Fisioterapia");
        faculdade3.addCurso(fisioterapia);


        Cadeira alg1 = new Cadeira("Algoritmos e estruturas de Dados I", "ALG1");
        engInformatica.addCadeira(alg1);

        Cadeira alg2 = new Cadeira("Algoritmos e estruturas de Dados II", "ALG2");
        engInformatica.addCadeira(alg2);

        Cadeira lp1 = new Cadeira("Linguagens de Programação I", "LP1");
        engInformatica.addCadeira(lp1);

        Cadeira lp2 = new Cadeira("Linguagens de Programação II", "LP2");
        engInformatica.addCadeira(lp2);

        Cadeira so = new Cadeira("Sistemas Operativos", "SO");
        engInformatica.addCadeira(so);

        Cadeira esof = new Cadeira("Engenharia de Software", "ESOF");
        engInformatica.addCadeira(esof);

        Cadeira fisica = new Cadeira("Fisica", "FIS");
        engCivil.addCadeira(fisica);

        Cadeira materiais = new Cadeira("Materiais de Construção", "MAT");
        engCivil.addCadeira(materiais);

        Cadeira resistencia = new Cadeira("Resistência de Materiais", "RES");
        engCivil.addCadeira(resistencia);

        Cadeira psocial = new Cadeira("Psicologia Social", "PSO");
        psicologia.addCadeira(psocial);

        Cadeira neuro = new Cadeira("Neuropsicologia", "NPS");
        psicologia.addCadeira(neuro);

        Cadeira juridica = new Cadeira("Psicologia Jurídica", "PSJ");
        psicologia.addCadeira(juridica);

        Cadeira fisiologia = new Cadeira("Anatomofisiologia", "AFI");
        fisioterapia.addCadeira(fisiologia);

        Cadeira bioquimica = new Cadeira("Bioquimica Fisiológica", "BFI");
        fisioterapia.addCadeira(bioquimica);

        Cadeira motricidade = new Cadeira("Motricidade Humana", "MHU");
        fisioterapia.addCadeira(motricidade);

        Aluno valter = new Aluno("Valter Cardoso", 31062);
        engInformatica.addAluno(valter);

        Aluno gustavo = new Aluno("Gustavo Teixeira", 21736);
        engInformatica.addAluno(gustavo);

        Aluno manuel = new Aluno("Manuel António", 13975);
        engCivil.addAluno(manuel);

        Aluno maria = new Aluno("Maria Aparecida", 33971);
        psicologia.addAluno(maria);

        Aluno jose = new Aluno("José Manuel", 25344);
        fisioterapia.addAluno(jose);

        Idioma portugues = new Idioma("Português", "PT");
        Idioma portuguesBr = new Idioma("Português-BR", "PT-BR");
        Idioma espanhol = new Idioma("Espanhol", "ES");
        Idioma inglesUk = new Idioma("Inglês-UK", "EN-UK");
        Idioma coreano = new Idioma("Coreano", "KOR");

        Explicador alessandro = new ExplicadorBuilder().setNome("Alessandro Moreira").setNumero(21064)
                .addCadeira(so)
                .addCadeira(esof)
                .addCadeira(lp2)
                .addIdioma(portuguesBr)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.MONDAY,
                        LocalTime.of(13, 00, 00),
                        LocalTime.of(17, 00, 00)))
                .addHorario(new Horario(DayOfWeek.WEDNESDAY,
                        LocalTime.of(9, 00, 00),
                        LocalTime.of(12, 00, 00)))
                .build();

        Explicador feliz = new ExplicadorBuilder().setNome("Feliz Gouveia").setNumero(11211)
                .addCadeira(esof)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.WEDNESDAY,
                        LocalTime.of(17, 00, 00),
                        LocalTime.of(20, 00, 00)))
                .build();

        Explicador jobs = new ExplicadorBuilder().setNome("Steve Jobs").setNumero(1333)
                .addCadeira(lp1)
                .addCadeira(lp2)
                .addIdioma(inglesUk).build();

        Explicador bill = new ExplicadorBuilder().setNome("Bill Gates").setNumero(10567)
                .addCadeira(alg1)
                .addCadeira(alg2)
                .addIdioma(inglesUk).build();

        Explicador edgar = new ExplicadorBuilder().setNome("Edgar Cardoso").setNumero(11767)
                .addCadeira(resistencia)
                .addCadeira(materiais)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addIdioma(espanhol).build();

        Explicador freud = new ExplicadorBuilder().setNome("Sigmund Freud").setNumero(456)
                .addCadeira(neuro)
                .addCadeira(psocial)
                .addIdioma(inglesUk).build();

        Explicador kim = new ExplicadorBuilder().setNome("Kim Jang").setNumero(12909)
                .addCadeira(motricidade)
                .addCadeira(fisiologia)
                .addIdioma(inglesUk)
                .addIdioma(coreano).build();

        Atendimento valAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2019-12-30T13:00:00"))
                .setAluno(valter)
                .setCadeira(esof)
                .setIdioma(portugues)
                .setExplicador(alessandro)
                .build();

        Atendimento gustAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2020-01-01T11:00:00"))
                .setAluno(gustavo)
                .setCadeira(so)
                .setIdioma(portuguesBr)
                .setExplicador(alessandro)
                .build();


        this.faculdadeRepo.save(faculdade1);
        this.faculdadeRepo.save(faculdade2);
        this.faculdadeRepo.save(faculdade3);

        System.out.println(this.faculdadeRepo.count() + " " + this.faculdadeRepo.findAll());
        System.out.println(this.cursoRepo.count() + " " + this.cursoRepo.findAll());
        System.out.println(this.cadeiraRepo.count() + " " + this.cadeiraRepo.findAll());
        System.out.println(this.alunoRepo.count() + " " + this.alunoRepo.findAll());
        System.out.println(this.explicadorRepo.count() + " " + this.explicadorRepo.findAll());
        System.out.println(this.idiomaRepo.count() + " " + this.idiomaRepo.findAll());
        System.out.println(this.horarioRepo.count() + " " + this.horarioRepo.findAll());
        System.out.println(this.atendimentoRepo.count() + " " + this.atendimentoRepo.findAll());

/*
        ArrayList<Aluno> alunos=inputNomesManualAtePonto();
        imprimirAlunos(alunos);
*/


    }

    private ArrayList<Aluno> inputNomesManualAtePonto(){

        Scanner scanner=new Scanner(System.in);
        String nome="";
        String aux="";
        Integer numero=null;
        Integer aux_int=null;
        boolean check=false;

        ArrayList<Aluno> alunos = new ArrayList<>();

        System.out.println("\nIntroduza o nome e respectivo numero de um aluno que deseja adicionar na base de dados(para terminar pressione a tecla \".\" na insercao do nome): \n");
        while(true) {

            System.out.print("Nome: ");

            try {
                nome = scanner.nextLine();

                check=checkForDigitsAndWhiteSpaces(nome);

                if(check){

                    System.out.println("\nErro na introducao do nome, volte a tentar!!!\n");
                    //aux=scanner.nextLine();
                    continue;
                }


                if (nome.equals(".")) {

                    break;
                }
                System.out.print("Numero: ");
                numero = scanner.nextInt();
                System.out.println();
                aux=scanner.nextLine();

            }catch(InputMismatchException ime){

                System.out.println("\nErro na introducao do numero, volte a tentar!!!\n");
                aux=scanner.nextLine();
                continue;
            }

            alunos.add(new Aluno(nome, numero));

        }

        return alunos;
    }

    private void imprimirAlunos(ArrayList<Aluno> alunos){

        System.out.println("\nImpressao do array list alunos que vai ser guardado na base de dados: ");
        System.out.println("---------------------------------------------------------------------");
        for(Aluno aluno : alunos){

            System.out.println("Nome: " + aluno.getNome() + "\t" + "Numero: " + aluno.getNumero());
        }
        System.out.println();
    }

    private boolean checkForDigitsAndWhiteSpaces(String nome){

        char[] ch;
        ch = nome.toCharArray();
        for(int i=0;i<ch.length;i++){

            if((ch[i]>='0' && ch[i]<='9') || (ch[i] == '\n') || (ch[i] == '\t') || (ch[i] == '\r')){

                return true;
            }
        }

        return false;
    }
}
