package com.nescodev.services;

import com.nescodev.entities.Laptop;
import com.nescodev.repositories.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LaptopServiceTest {
    @Autowired
    private LaptopService laptopService;
    @MockBean
    private LaptopRepository laptopRepository;

    private Laptop laptop;

    @BeforeEach
    void setUp() {
        laptop = new Laptop("Mac", 13F, 8);
        laptop.setId(1L);
    }

    @Test
    void buscarTodos() {
        when(laptopRepository.findAll()).thenReturn(List.of(laptop));
        List<Laptop> resultado = laptopService.buscarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertIterableEquals(List.of(laptop), resultado);
    }

    @Test
    @DisplayName("Test findById OK")
    void buscarUnoPorId() {

        when(laptopRepository.findById(1L)).thenReturn(Optional.of(laptop));
        ResponseEntity<Laptop> respuesta = laptopService.buscarUnoPorId(1L);

        assertNotNull(respuesta.getBody());
        assertEquals(HttpStatusCode.valueOf(200), respuesta.getStatusCode());
        assertEquals(laptop, respuesta.getBody());
    }

    @Test
    @DisplayName("Test findById NotFound")
    void buscarUnoPorIdNofFound() {
        when(laptopRepository.findById(1L)).thenReturn(Optional.of(laptop));
        ResponseEntity<Laptop> respuesta = laptopService.buscarUnoPorId(2L);

        assertNull(respuesta.getBody());
        assertEquals(HttpStatusCode.valueOf(404), respuesta.getStatusCode());
    }

    @Test
    void guardarLaptop() {
        when(laptopRepository.save(any())).thenReturn(laptop);
        Laptop respuesta = laptopService.guardarLaptop(laptop);

        assertEquals(laptop, respuesta);
    }

    @Test
    void actualizar() {
        when(laptopRepository.existsById(1L)).thenReturn(true);
        when(laptopRepository.getReferenceById(1L)).thenReturn(laptop);
        when(laptopRepository.save(any())).thenReturn(laptop);
        ResponseEntity<Laptop> respuesta = laptopService.actualizar(laptop,1L);

        assertEquals(HttpStatusCode.valueOf(200),respuesta.getStatusCode());
        assertEquals(laptop,respuesta.getBody());

    }

    @Test
    void eliminar() {
        doNothing().when(laptopRepository).deleteById(any());
        laptopService.eliminar(1L);
        verify(laptopRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void eliminarTodos() {
        doNothing().when(laptopRepository).deleteAll();
        laptopService.eliminarTodos();
        verify(laptopRepository, times(1)).deleteAll();
    }
}