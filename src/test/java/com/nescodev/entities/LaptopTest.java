package com.nescodev.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaptopTest {

    Laptop laptop;

    @Test
    void laptopConIdNull() {
        laptop = new Laptop("Toshiba", 13F, 4);
        assertAll("Atributos Laptop",
                ()-> assertNotNull(laptop),
                ()-> assertEquals("Toshiba",laptop.getMarca()),
                ()-> assertEquals(13F,laptop.getTamanoPantalla()),
                ()-> assertEquals(4,laptop.getMemoria()));
    }

}