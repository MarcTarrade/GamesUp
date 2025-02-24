package com.gamesUP.gamesUP.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AvisControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAddAvis() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/avis")
                .content("{\"commentaire\": \"Bon\", \"note\": 5}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentaire").value("Bon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.note").value(5))
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetAllAvis() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/avis");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].commentaire", hasItem("Bon")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].note", hasItem(5)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testFindAvisById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/avis/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentaire").value("Bon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.note").value(5))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPatchAvis() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/avis/1")
                .content("{\"commentaire\":\"Tres bon\"}")
                        .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentaire").value("Tres bon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.note").value(5))
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDeleteAvis() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/avis/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}