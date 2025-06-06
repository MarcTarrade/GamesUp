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
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAddAuthor() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/author")
                .content("{\"name\": \"Thiercelieux\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Thiercelieux"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetAllAuthor() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/author");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name", hasItem("Thiercelieux")))
                .andExpect(status().isOk());
    }
    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testFindAuthorById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/author/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Thiercelieux"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPatchAuthor() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/author/1")
                .content("{\"name\":\"Thiercelieu\"}")
                        .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Thiercelieu"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDeleteAuthor() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/author/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}