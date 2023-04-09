package com.nescodev.controllers;

import com.nescodev.entities.Laptop;
import com.nescodev.services.LaptopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Laptop", description = "API de Laptops")
@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    private LaptopService laptopService;
    @Value("${mi.variable}")
    private String entorno;
    @Value("${mi.env}")
    private String miVarEnv;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping("/saludo")
    public String saludo(){
        return "Perfil: "+ entorno+ "\nSistema Operativo: "+miVarEnv;
    }
    @Operation(summary = "Buscar todos los Laptops")
    @GetMapping
    public List<Laptop> findAll() {
        return laptopService.buscarTodos();
    }
    @Operation(summary = "Buscar Laptops por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Laptop> findOneById(@Parameter @PathVariable Long id) {
        return laptopService.buscarUnoPorId(id);
    }
    @Operation(summary = "Crear un Laptop ")
    @PostMapping
    public Laptop save(@RequestBody Laptop laptop) {
        return laptopService.guardarLaptop(laptop);
    }
    @Operation(summary = "Modificar un Laptop por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop, @PathVariable Long id) {
        return laptopService.actualizar(laptop, id);
    }
    @Operation(summary = "Eliminar un Laptop por ID")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        laptopService.eliminar(id);
    }
    @Operation(summary = "Eliminar todos los Laptops")
    @DeleteMapping
    public void deleteAll(){
        laptopService.eliminarTodos();
    }
}
