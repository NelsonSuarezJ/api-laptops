package com.nescodev.controllers;

import com.nescodev.entities.Laptop;
import com.nescodev.services.LaptopService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebMvcTest(LaptopController.class)
public class LaptopControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaptopService laptopService;

    private static String END_POINT = "/api/laptops";

    @Test
    void retornarTodosLosLaptops() throws Exception {
        Laptop laptop = new Laptop("Acer", 12F, 8);

        Mockito.when(laptopService.buscarTodos()).thenReturn(List.of(laptop));
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].marca", is(laptop.getMarca())));
    }
}
