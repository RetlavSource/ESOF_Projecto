package com.projeto.gestao_explicacoes.models.DTO;

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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExplicadorDTO {

    private String nome;
    private Integer numero;

    private Set<Horario> horarios = new HashSet<>();
    private Set<HorarioDTO> horariosDisponiveis = new HashSet<>();

    private Set<Idioma> idiomas = new HashSet<>();
    private Set<IdiomaDTO> idiomasLeccionados = new HashSet<>();

    private Set<Atendimento> atendimentos = new HashSet<>();
    private Set<AtendimentoDTO> atendimentosMarcados = new HashSet<>();

    private Set<Cadeira> cadeiras = new HashSet<>();
    private Set<CadeiraDTO> cadeirasLeccionadas = new HashSet<>();


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

    /**
     * Copia os Set's dos modelos para Set's de DTO's
     */
    public void allSetToDTO() {
        if (this.horarios != null) {
            for (Horario horario : this.horarios) {
                HorarioDTO horarioDTO = new HorarioDTO();
                horarioDTO.setDiaSemana(horario.getDiaSemana());
                horarioDTO.setHoraInicio(horario.getHoraInicio());
                horarioDTO.setHoraFim(horario.getHoraFim());
                this.horariosDisponiveis.add(horarioDTO);
            }
        }
        this.horarios = null;

        if (this.idiomas != null) {
            for (Idioma idioma : this.idiomas) {
                IdiomaDTO idiomaDTO = new IdiomaDTO();
                idiomaDTO.setNome(idioma.getNome());
                idiomaDTO.setSigla(idioma.getSigla());
                this.idiomasLeccionados.add(idiomaDTO);
            }
        }
        this.idiomas = null;

        if (this.atendimentos != null) {
            for (Atendimento atendimento : this.atendimentos) {
                AtendimentoDTO atendimentoDTO = new AtendimentoDTO();
                atendimentoDTO.setData(atendimento.getData());
                atendimentoDTO.setNomeAluno(atendimento.getAluno().getNome());
                atendimentoDTO.setNumeroAluno(atendimento.getAluno().getNumero());
                atendimentoDTO.setNomeCadeira(atendimento.getCadeira().getNome());
                atendimentoDTO.setNomeIdioma(atendimento.getIdioma().getNome());
                this.atendimentosMarcados.add(atendimentoDTO);
            }
        }
        this.atendimentos = null;

        if (this.cadeiras != null) {
            for (Cadeira cadeira : this.cadeiras) {
                CadeiraDTO cadeiraDTO = new CadeiraDTO();
                cadeiraDTO.setNome(cadeira.getNome());
                cadeiraDTO.setSigla(cadeira.getSigla());
                this.cadeirasLeccionadas.add(cadeiraDTO);
            }
        }
        this.cadeiras = null;
    }
}
