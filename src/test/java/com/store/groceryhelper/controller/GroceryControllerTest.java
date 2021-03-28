package com.store.groceryhelper.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.groceryhelper.model.Category;
import com.store.groceryhelper.model.Grocery;
import com.store.groceryhelper.service.GroceryService;


@WebMvcTest(GroceryController.class)
public class GroceryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroceryService groceryService;

    @Test
    public void shouldCreateGrocery() throws Exception {
        when(groceryService.createGrocery(any())).then(i -> i.getArgument(0, Grocery.class));
        mockMvc.perform(post("/grocery")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(buildGrocery("apple", Category.FRUIT)))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("apple"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("FRUIT"));

    }

    @Test
    public void shouldUpdateGrocery() throws Exception {
        var apple  = buildGrocery("apple", Category.FRUIT);
        when(groceryService.updateGrocery(eq(1), any())).thenReturn(apple);
        mockMvc.perform(put("/grocery/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(apple))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("apple"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("FRUIT"));
    }

    @Test
    public void shouldGetGroceries() throws Exception {
        var apple  = buildGrocery("apple", Category.FRUIT);
        when(groceryService.getGroceryByCategory(Category.FRUIT)).thenReturn(Arrays.asList(apple));
        mockMvc.perform(get("/grocery?category=FRUIT"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0]name").value("apple"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0]category").value("FRUIT"));
    }

    @Test
    public void shouldGetGrocery() throws Exception {
        var apple  = buildGrocery("apple", Category.FRUIT);
        when(groceryService.getGrocery(1)).thenReturn(apple);
        mockMvc.perform(get("/grocery/1"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("apple"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("FRUIT"));
    }


    private Grocery buildGrocery(String name, Category category) {
        return Grocery.builder()
            .id(1)
            .name(name)
            .category(category).build();
    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}