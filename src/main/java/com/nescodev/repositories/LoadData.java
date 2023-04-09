package com.nescodev.repositories;

import com.nescodev.entities.Laptop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class LoadData {

    private LaptopRepository laptopRepository;
    public LoadData(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Bean
    public void cargarDatos(){
        laptopRepository.save(new Laptop("Hp",14f,4));
        laptopRepository.save(new Laptop("Mac",13f,8));
    }
}
