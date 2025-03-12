package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.repository.GameRepository;
import com.gamesUP.gamesUP.repository.InventoryRepository;
import com.gamesUP.gamesUP.repository.UserRepository;
import com.gamesUP.gamesUP.repository.WishlistRepository;
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
public class InventoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    GameRepository gameRepository;
    @MockBean
    InventoryRepository inventoryRepository;

    @Test
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAddInventory() throws Exception {
        Game game = new Game(1, "loup garou");
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/inventory")
                .content("{\"game\": { \"id\": 1, \"nom\": \"loup garou\" }}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetAllInventory() throws Exception {
        List<Inventory> inventory = List.of(new Inventory(1L, new Game(1, "loup garou")));

        when(inventoryRepository.findAll()).thenReturn(inventory);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", hasItem(1)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testFindInventoryById() throws Exception {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(new Inventory(1L, new Game(1, "loup garou"))));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPatchInventory() throws Exception {
        Game game = new Game(2, "Catan");
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(new Inventory(1L, game)));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/inventory/1")
                .content("{\"game\": { \"id\": 2, \"nom\": \"Catan\" }}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDeleteInventory() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/inventory/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}