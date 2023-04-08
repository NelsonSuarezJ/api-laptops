package com.nescodev.services;

import com.nescodev.entities.Laptop;
import com.nescodev.repositories.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    private LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public List<Laptop> buscarTodos() {
        return laptopRepository.findAll();
    }

    public ResponseEntity<Laptop> buscarUnoPorId(Long id) {
        Optional<Laptop> optional = laptopRepository.findById(id);

        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(optional.get());
    }

    public Laptop guardarLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public ResponseEntity<Laptop> actualizar(Laptop laptopUp, Long id) {
        if (laptopRepository.existsById(id)) {
            Laptop laptop = laptopRepository.getReferenceById(id);
            laptop.setMarca(laptopUp.getMarca());
            laptop.setTamanoPantalla(laptopUp.getTamanoPantalla());
            laptop.setMemoria(laptopUp.getMemoria());
            return ResponseEntity.ok(laptopRepository.save(laptop));
        }
        return ResponseEntity.notFound().build();
    }

    public void eliminar(Long id){
        laptopRepository.deleteById(id);
    }

    public void eliminarTodos(){
        laptopRepository.deleteAll();
    }
}
