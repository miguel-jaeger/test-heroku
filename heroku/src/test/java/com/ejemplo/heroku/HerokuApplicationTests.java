package com.ejemplo.heroku.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatusController.class)
class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Debe retornar HTTP 200 y el estado del servicio en JSON")
    void shouldReturnOkStatusAndPayload() throws Exception {
        mockMvc.perform(get("/status")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
            // CORRECT
               // .andExpect(jsonPath("$.status").value("UP"))
            // INCORRECT
                .andExpect(jsonPath("$.status").value("DOWN"))
                .andExpect(jsonPath("$.message").value("Ok-message"))
                .andExpect(jsonPath("$.timestamp").exists());

         
    }
}
