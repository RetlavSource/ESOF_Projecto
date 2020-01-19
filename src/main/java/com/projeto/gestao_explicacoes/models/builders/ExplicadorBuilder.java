package com.projeto.gestao_explicacoes.models.builders;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.*;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorBuilder {

    private String nome;
    private Integer numero;
    private Set<Horario> horarios = new HashSet<>();
    private Set<Idioma> idiomas = new HashSet<>();
    private Set<Atendimento> atendimentos = new HashSet<>();
    private Set<Cadeira> cadeiras = new HashSet<>();

    public ExplicadorBuilder setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        } else {
            this.nome = "";
        }
        return this;
    }

    public ExplicadorBuilder setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public ExplicadorBuilder setHorario(Set<Horario> horario) {
        if ( horario != null ) {
            this.horarios = horario;
        }
        return this;
    }

    public ExplicadorBuilder addHorario(Horario horario) {
        this.horarios.add(horario);
        return this;
    }

    public ExplicadorBuilder setIdiomas(Set<Idioma> idiomas) {
        if ( idiomas != null ) {
            this.idiomas = idiomas;
        }
        return this;
    }

    public ExplicadorBuilder addIdioma(Idioma idioma) {
        this.idiomas.add(idioma);
        return this;
    }

    public ExplicadorBuilder setAtendimentos(Set<Atendimento> atendimentos) {
        if ( atendimentos != null ) {
            this.atendimentos = atendimentos;
        }
        return this;
    }

    public ExplicadorBuilder addAtendimento(Atendimento atendimento) {
        this.atendimentos.add(atendimento);
        return this;
    }

    public ExplicadorBuilder setCadeiras(Set<Cadeira> cadeiras) {
        if ( cadeiras != null ) {
            this.cadeiras = cadeiras;
        }
        return this;
    }

    public ExplicadorBuilder addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
        return this;
    }

    public Explicador build() throws FalhaCriarException{

        if( this.nome == null || this.nome.isBlank() ){
            throw new FalhaCriarException("Argumento nome inválido ou inexistente!!");
        }

        if( this.numero == null || this.numero == 0 ){
            throw new FalhaCriarException("Argumento numero inválido ou inexistente!!");
        }

        Explicador explicador = new Explicador(nome, numero, horarios, idiomas, atendimentos, cadeiras);

        if ( !this.horarios.isEmpty() ) {
            for (Horario horario: this.horarios) {
                horario.setExplicador(explicador);
            }
        }

        if ( !this.idiomas.isEmpty() ) {
            for (Idioma idioma: this.idiomas) {
                idioma.getExplicadores().add(explicador);
            }
        }

        if ( !this.atendimentos.isEmpty() ) {
            for (Atendimento atendimento : this.atendimentos) {
                atendimento.setExplicador(explicador);
            }
        }

        if ( !this.cadeiras.isEmpty() ) {
            for (Cadeira cadeira: this.cadeiras) {
                cadeira.getExplicadores().add(explicador);
            }
        }
        return explicador;
    }

}
