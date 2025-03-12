package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.repository.UserRepository;
import com.gamesUP.gamesUP.repository.PurchaseRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PurchaseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PurchaseRepository purchaseRepository;

    @Test
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAddPurchase() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/purchase")
                .content("{\"paid\": false}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetAllPurchase() throws Exception {
        List<Purchase> purchase = List.of(new Purchase(1L, false));

        when(purchaseRepository.findAll()).thenReturn(purchase);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", hasItem(1)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testFindPurchaseById() throws Exception {
        when(purchaseRepository.findById(1L)).thenReturn(Optional.of(new Purchase(1L, false)));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/purchase/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPatchPurchase() throws Exception {
        when(purchaseRepository.findById(1L)).thenReturn(Optional.of(new Purchase(1L, true)));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/purchase/1")
                .content("{\"user\": { \"id\": 2, \"nom\": \"test2\" }}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDeletePurchase() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/purchase/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}