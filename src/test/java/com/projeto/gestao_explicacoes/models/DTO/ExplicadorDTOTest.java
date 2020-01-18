package com.projeto.gestao_explicacoes.models.DTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ExplicadorDTOTest {

    @Test
    void ignoredAttributesWithJsonIgnore() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        ExplicadorDTO dtoObject = new ExplicadorDTO();

        String dtoAsString = mapper.writeValueAsString(dtoObject);

        assertThat(dtoAsString, not(containsString("nome")));
        assertThat(dtoAsString, not(containsString("numero")));
        assertThat(dtoAsString, not(containsString("horarios")));
        assertThat(dtoAsString, not(containsString("idiomas")));

    }
}