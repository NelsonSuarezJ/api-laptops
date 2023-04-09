package com.nescodev.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private Float tamanoPantalla;
    private Integer memoria;

    public Laptop(){}

    public Laptop(String marca, Float tamanoPantalla, Integer memoria) {
        this.marca = marca;
        this.tamanoPantalla = tamanoPantalla;
        this.memoria = memoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Float getTamanoPantalla() {
        return tamanoPantalla;
    }

    public void setTamanoPantalla(Float tamanoPantalla) {
        this.tamanoPantalla = tamanoPantalla;
    }

    public Integer getMemoria() {
        return memoria;
    }

    public void setMemoria(Integer memoria) {
        this.memoria = memoria;
    }
}

