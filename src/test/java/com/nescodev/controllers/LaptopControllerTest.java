package com.nescodev.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nescodev.entities.Laptop;
import com.nescodev.repositories.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LaptopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LaptopRepository laptopRepository;

    private static final String END_POINT_BASE = "/api/laptops";
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        Laptop laptop = new Laptop("Mac", 14F, 4);
        ObjectMapper objectMapper = new ObjectMapper();
        json = objectMapper.writeValueAsString(laptop);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get(END_POINT_BASE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].marca").value("MAC1"));
    }

    @Test
    void findOneById() throws Exception {
        mockMvc.perform(get(END_POINT_BASE + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void save() throws Exception {

        mockMvc.perform(post(END_POINT_BASE).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.marca").value("Mac"));

    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put(END_POINT_BASE + "/2").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.marca").value("Mac"));
    }

    @Test
    @Transactional
    void deleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_BASE + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_BASE))
                .andExpect(status().isOk());
    }
}