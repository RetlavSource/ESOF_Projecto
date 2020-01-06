package com.projeto.gestao_explicacoes.models.builders;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Aluno;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Curso;

import java.util.HashSet;
import java.util.Set;

public class AlunoBuilder {

    private String nome;
    private Integer numero;
    private Curso curso;
    private Set<Atendimento> atendimentos = new HashSet<>();


    public AlunoBuilder setNome(String nome){

        this.nome=nome;
        return this;
    }

    public AlunoBuilder setNumero(Integer numero){

        this.numero=numero;
        return this;
    }

    public AlunoBuilder setCurso(Curso curso){

        this.curso=curso;
        return this;
    }

    public AlunoBuilder setAtendimentos(Set<Atendimento> atendimentos){

        this.atendimentos=atendimentos;
        return this;
    }

    public AlunoBuilder addAtendimento(Atendimento atendimento){

        this.atendimentos.add(atendimento);
        return this;
    }

    public Aluno build() throws FalhaCriarException{

        Aluno aluno = new Aluno(nome,numero);

        if(nome == null){

            throw new FalhaCriarException("Argumento aluno inválido ou inexistente!");
        }

        if(numero == null){

            throw new FalhaCriarException("Argumento numero inválido ou inexistente!");
        }

        if(curso != null){

            this.curso.addAluno(aluno);

        }/*else{

            throw new FalhaCriarException("Argumento curso inválido ou inexistente!");
        }
        */

        if(atendimentos != null) {

            for (Atendimento atendimento : this.atendimentos) {

                atendimento.setAluno(aluno);
            }

        }/*else{

            throw new FalhaCriarException("Argumento atendimento inválido ou inexistente!");
        }

        */

        return aluno;
    }

}
