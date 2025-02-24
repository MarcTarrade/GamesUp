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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAddUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .content("{\"nom\": \"client\",\"password\": \"$2a$10$UJIJY5tNje6tH6G4gTJUqu4OvoDab0lG5Naw1aLwf.mTVEYhy08f2\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("client"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2a$10$UJIJY5tNje6tH6G4gTJUqu4OvoDab0lG5Naw1aLwf.mTVEYhy08f2"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetAllUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].nom", hasItem("client")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].password", hasItem("$2a$10$UJIJY5tNje6tH6G4gTJUqu4OvoDab0lG5Naw1aLwf.mTVEYhy08f2")))
                .andExpect(status().isOk());
    }
    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testFindUserById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("client"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2a$10$UJIJY5tNje6tH6G4gTJUqu4OvoDab0lG5Naw1aLwf.mTVEYhy08f2"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPatchUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/user/1")
                .content("{\"nom\":\"client2\"}")
                        .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("client2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2a$10$UJIJY5tNje6tH6G4gTJUqu4OvoDab0lG5Naw1aLwf.mTVEYhy08f2"))
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDeleteUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}