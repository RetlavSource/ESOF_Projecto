package com.projeto.gestao_explicacoes.models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AtendimentoDTO {

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;
    private String nomeExplicador;
    private Integer numeroExplicador;
    private String nomeAluno;
    private Integer numeroAluno;
    private String nomeCadeira;
    private String siglaCadeira;
    private String nomeIdioma;
    private String siglaIdioma;

    public AtendimentoDTO(LocalDateTime data, String nomeExplicador, String nomeAluno, String nomeCadeira, String nomeIdioma) {
        this.data = data;
        this.nomeExplicador = nomeExplicador;
        this.nomeAluno = nomeAluno;
        this.nomeCadeira = nomeCadeira;
        this.nomeIdioma = nomeIdioma;
    }
}
