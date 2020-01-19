package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Cadeira;
import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.repositories.*;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.FilterExplicadorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExplicadorServiceDB.class)
class ExplicadorServiceDBTest {

    @MockBean
    private HorarioRepo horarioRepo;
    @MockBean
    private IdiomaRepo idiomaRepo;
    @MockBean
    private AtendimentoRepo atendimentoRepo;
    @MockBean
    private FilterExplicadorService filterExplicadorService;

    @MockBean
    private ExplicadorRepo explicadorRepo;

    @MockBean
    private CadeiraRepo cadeiraRepo;

    @Test
    void adicionaCadeiraAoExplicador() {

        ExplicadorDTO infoExplicador = new ExplicadorDTO();
        infoExplicador.setNome("Explicador1");
        infoExplicador.setNumero(123);

        Explicador explicador = new Explicador();
        explicador.setNome("Explicador1");
        explicador.setNumero(123);

        String nomeCadeira = "Engenharia de Software";
        Cadeira cadeira = new Cadeira();

        when(this.explicadorRepo.findByNumero(infoExplicador.getNumero())).thenReturn(Optional.of(explicador));
        when(this.cadeiraRepo.findByNome(nomeCadeira)).thenReturn(Optional.of(cadeira));

        explicador.addCadeira(cadeira);

        assertEquals(1 , explicador.getCadeiras().size());



        ExplicadorDTO infoExplicador2 = new ExplicadorDTO();
        Explicador explicador2 = new Explicador();
        when(this.explicadorRepo.findByNumero(infoExplicador2.getNumero())).thenReturn(Optional.empty());

        assertEquals(0 , explicador2.getCadeiras().size());

        ExplicadorDTO infoExplicador3 = new ExplicadorDTO();
        Explicador explicador3 = new Explicador();
        when(this.explicadorRepo.findByNumero(infoExplicador3.getNumero())).thenReturn(Optional.of(explicador3));
        when(this.cadeiraRepo.findByNome(nomeCadeira)).thenReturn(Optional.empty());

        assertEquals(0 , explicador3.getCadeiras().size());


    }
}