package com.verdevivo.verdevivo.controller;

import com.verdevivo.verdevivo.model.Planta;
import com.verdevivo.verdevivo.service.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plantas")
@CrossOrigin(origins = "*") // Permite peticiones desde un frontend (React, Angular, etc.)
public class PlantaController {

    @Autowired
    private PlantaService plantaService;

    // Listar todas las plantas (GET /api/plantas)
    @GetMapping
    public List<Planta> listar() {
        return plantaService.listarTodas();
    }

    // Obtener una planta por ID (GET /api/plantas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Planta> obtenerPorId(@PathVariable Long id) {
        return plantaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear planta (POST /api/plantas)
    @PostMapping
    public Planta crear(@RequestBody Planta planta) {
        return plantaService.guardar(planta);
    }

    // Actualizar planta (PUT /api/plantas/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Planta> actualizar(@PathVariable Long id, @RequestBody Planta plantaDetalles) {
        return plantaService.obtenerPorId(id).map(planta -> {
            planta.setNombre(plantaDetalles.getNombre());
            planta.setTipo(plantaDetalles.getTipo());
            planta.setPrecio(plantaDetalles.getPrecio());
            planta.setStock(plantaDetalles.getStock());
            return ResponseEntity.ok(plantaService.guardar(planta));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar planta (DELETE /api/plantas/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (plantaService.obtenerPorId(id).isPresent()) {
            plantaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}