package com.projeto.gestao_explicacoes.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Horario;
import com.projeto.gestao_explicacoes.models.Idioma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExplicadorDTO {

    private String nome;
    private Integer numero;
    private Set<Horario> horarios;
    private Set<Idioma> idiomas;
    private Set<Atendimento> atendimentos;
    private Set<Cadeira> cadeiras;

    public ExplicadorDTO(String nome, Integer numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public ExplicadorDTO(String nome, Integer numero, Set<Horario> horarios, Set<Idioma> idiomas, Set<Cadeira> cadeiras) {
        this.nome = nome;
        this.numero = numero;
        if (!horarios.isEmpty()) {
            this.horarios = horarios;
        }
        if (!idiomas.isEmpty()) {
            this.idiomas = idiomas;
        }
        if (!cadeiras.isEmpty()) {
            this.cadeiras = cadeiras;
        }
    }
}
