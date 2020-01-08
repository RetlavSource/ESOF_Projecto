package com.projeto.gestao_explicacoes.services.atendimentoServices.filters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtendimentoObject {

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;
    private String nomeExplicador;
    private String numeroExplicador;
    private String nomeAluno;
    private String numeroAluno;
    private String nomeCadeira;
    private String siglaCadeira;
    private String nomeIdioma;
    private String siglaIdioma;
}
