package com.nescodev.repositories;

import com.nescodev.entities.Laptop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class LaptopRepositoryTest {
    @Autowired
    private LaptopRepository laptopRepository;
    private Laptop laptop;

    @BeforeEach
    public void setup() {
        laptop = new Laptop("Samsung", 10f, 6);
        laptopRepository.save(laptop);
    }

    @Test
    public void testLaptopById() {
        Optional<Laptop> optional = laptopRepository.findById(laptop.getId());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(laptop.getMarca(), optional.get().getMarca());
        Assertions.assertEquals(laptop.getTamanoPantalla(), optional.get().getTamanoPantalla());
        Assertions.assertEquals(laptop.getMemoria(), optional.get().getMemoria());

    }
}