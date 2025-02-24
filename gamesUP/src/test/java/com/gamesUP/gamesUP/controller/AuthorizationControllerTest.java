package com.gamesUP.gamesUP.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn401() throws Exception {
        mockMvc.perform(get("/game")).andExpect(status().isUnauthorized());
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void shouldReturn200() throws Exception {
        mockMvc.perform(get("/game")).andExpect(status().isOk());
    }
}
