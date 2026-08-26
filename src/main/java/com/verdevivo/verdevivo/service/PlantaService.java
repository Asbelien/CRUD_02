package com.verdevivo.verdevivo.service;

import com.verdevivo.verdevivo.model.Planta;
import com.verdevivo.verdevivo.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {

    @Autowired
    private PlantaRepository plantaRepository;

    public List<Planta> listarTodas() {
        return plantaRepository.findAll();
    }

    public Optional<Planta> obtenerPorId(Long id) {
        return plantaRepository.findById(id);
    }

    public Planta guardar(Planta planta) {
        return plantaRepository.save(planta);
    }

    public void eliminar(Long id) {
        plantaRepository.deleteById(id);
    }
}