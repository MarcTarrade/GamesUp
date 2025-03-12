package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.repository.UserRepository;
import com.gamesUP.gamesUP.repository.WishlistRepository;
import com.gamesUP.gamesUP.services.UserService;
import com.gamesUP.gamesUP.services.WishlistService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishlistControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;
    @MockBean
    WishlistRepository wishlistRepository;

    @Test
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAddWishlist() throws Exception {
        User user = new User(1L, "test");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wishlist")
                .content("{\"user\": { \"id\": 1, \"nom\": \"test\" }}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testGetAllWishlist() throws Exception {
        List<Wishlist> wishlist = List.of(new Wishlist(1L, new User(1L, "test")));

        when(wishlistRepository.findAll()).thenReturn(wishlist);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wishlist");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", hasItem(1)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testFindWishlistById() throws Exception {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(new Wishlist(1L, new User(1L, "test"))));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wishlist/1");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testPatchWishlist() throws Exception {
        User user = new User(2L, "test2");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(new Wishlist(1L, user)));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/wishlist/1")
                .content("{\"user\": { \"id\": 2, \"nom\": \"test2\" }}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testDeleteWishlist() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/wishlist/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}