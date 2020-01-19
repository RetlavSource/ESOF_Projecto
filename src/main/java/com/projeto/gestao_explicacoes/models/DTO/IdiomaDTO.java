package com.projeto.gestao_explicacoes.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IdiomaDTO {

    private String nome;
    private String sigla;

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public void setSigla(String sigla) {
        this.sigla = sigla.toUpperCase();
    }

}
