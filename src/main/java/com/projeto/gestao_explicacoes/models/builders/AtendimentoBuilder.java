package com.projeto.gestao_explicacoes.models.builders;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.*;

import java.time.LocalDateTime;

public class AtendimentoBuilder {

    private LocalDateTime data;
    private Explicador explicador;
    private Aluno aluno;
    private Cadeira cadeira;
    private Idioma idioma;


    public AtendimentoBuilder setData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public AtendimentoBuilder setExplicador(Explicador explicador) {
        this.explicador = explicador;
        return this;
    }

    public AtendimentoBuilder setAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public AtendimentoBuilder setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
        return this;
    }

    public AtendimentoBuilder setIdioma(Idioma idioma) {
        this.idioma = idioma;
        return this;
    }

    public Atendimento build() throws FalhaCriarException {

        if ( data == null ) {
            throw new FalhaCriarException("Argumento data inválido ou inexistente!!");
        }

        Atendimento atendimento = new Atendimento(data);

        if ( this.explicador != null ) {
            this.explicador.addAtendimento(atendimento);
        }else{
            throw new FalhaCriarException("Argumento explicador inválido ou inexistente!!");
        }


        if ( this.aluno != null ) {
            this.aluno.addAtendimento(atendimento);
        } else {
            throw new FalhaCriarException("Argumento aluno inválido ou inexistente!!");
        }

        if ( this.cadeira != null ) {
            this.cadeira.addAtendimento(atendimento);
        } else {
            throw new FalhaCriarException("Argumento cadeira inválido ou inexistente!!");
        }

        if ( this.idioma != null ) {
            this.idioma.addAtendimento(atendimento);
        } else {
            throw new FalhaCriarException("Argumento idioma inválido ou inexistente!!");
        }

        return atendimento;
    }
}
