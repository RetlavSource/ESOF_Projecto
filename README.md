[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/RetlavSource/ESOF_Projecto?color=green&include_prereleases&label=%C3%BAltima%20vers%C3%A3o&style=plastic)](https://github.com/RetlavSource/ESOF_Project/releases)

# Projeto Prático de Engenharia de Software

Este é um projeto prático para a disciplina de  ***Engenharia de Software*** na ***Universidade Fernando Pessoa***.

## Endpoints utilizados
*   **GET**
    *   /cadeira -- *`lista todas as cadeiras`*
    *   /cadeira/1 -- *`lista a cadeira com o id=1`*
    *   /atendimento -- *`lista todos os atendimentos`*
    *   /atendimento/1 -- *`lista o atendimento com o id=1`*
    *   /curso -- *`lista todos os cursos`*
    *   /curso/1 -- *`lista o curso com o id=1`*

*   **POST**
    *   /cadeira -- *`cria uma cadeira`*
    *   /cadeira/{curso} -- *`cria uma cadeira no curso com o id={curso} ou nome={curso}`*
    *   /atendimento -- *`cria um atendimento`*
    *   /curso/{faculdade} -- *`cria um curso na faculdade com id={faculdade} ou nome={faculdade}`*

*   **PUT**
    *   /cadeira/1 -- *`modifica a cadeira com o id=1`*
    *   /atendimento/1 -- *`modifica o atendimento com o id=1`*
    *   /curso/1 -- *`modifica o curso com o id=1`*
    
*   **DELETE**
    *   /cadeira/1 -- *`remove a cadeira com o id=1`*
    *   /atendimento/1 -- *`remove o atendimento com o id=1`*
    *   /curso/1 -- *`remove o curso com o id=1`*

## Documentação de referência
Documentação utilizada na realização do projeto:
*   [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
*   [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/maven-plugin/)
*   [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
*   [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
*   [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#using-boot-devtools)
*   [Thymeleaf](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

## Guias de utilização
Guias de utilização de algumas ferramentas utilizadas:

*   [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
*   [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
*   [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
*   [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
*   [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
*   [TODOS os guias](https://spring.io/guides/)