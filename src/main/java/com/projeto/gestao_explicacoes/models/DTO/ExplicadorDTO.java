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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExplicadorDTO {

    private String nome;
    private Integer numero;
    private Set<Horario> horarios = new HashSet<>();
    private Set<Idioma> idiomas = new HashSet<>();
    private Set<Atendimento> atendimentos = new HashSet<>();
    private Set<Cadeira> cadeiras = new HashSet<>();

    public ExplicadorDTO(String nome, Integer numero) {
        this.nome = nome;
        this.numero = numero;
    }

    /**
     * Recebe parâmetros do {@code Explicador} para converter em ExplicadorDTO
     *
     * @param nome
     * @param numero
     * @param horarios parâmetro igual/mesmo tipo que o {@code Explicador}
     * @param idiomas parâmetro igual/mesmo tipo que o {@code Explicador}
     * @param cadeiras parâmetro igual/mesmo tipo que o {@code Explicador}
     */
    public ExplicadorDTO(String nome, Integer numero, Set<Horario> horarios, Set<Idioma> idiomas, Set<Cadeira> cadeiras) {
        this.nome = nome;
        this.numero = numero;
        this.horarios = horarios;
        this.idiomas = idiomas;
        this.cadeiras = cadeiras;
    }
}
