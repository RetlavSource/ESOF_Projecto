package com.projeto.gestao_explicacoes.services.explicadorServices.filters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.gestao_explicacoes.models.Atendimento;
import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.Horario;
import com.projeto.gestao_explicacoes.models.Idioma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExplicadorDTO {

    private String nome;
    private Integer numero;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Horario> horarios = new HashSet<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Idioma> idiomas = new HashSet<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Atendimento> atendimentos = new HashSet<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Cadeira> cadeiras = new HashSet<>();

    public ExplicadorDTO(String nome, Integer numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public ExplicadorDTO(String nome, Integer numero, Set<Horario> horarios, Set<Idioma> idiomas, Set<Cadeira> cadeiras) {
        this.nome = nome;
        this.numero = numero;
        this.horarios = horarios;
        this.idiomas = idiomas;
        this.cadeiras = cadeiras;
    }
}
