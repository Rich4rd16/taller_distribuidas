package com.proyecto.controllers;

import com.proyecto.models.entities.Proyecto;
import com.proyecto.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Proyecto proyecto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(proyecto));
    }

    @GetMapping
    public List<Proyecto> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Proyecto> proyectoOptional = service.findById(id);
        if (proyectoOptional.isPresent()) {
            return ResponseEntity.ok().body(proyectoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Proyecto proyecto, @PathVariable Long id) {
        Optional<Proyecto> proyectoOptional = service.findById(id);
        if (proyectoOptional.isPresent()) {
            Proyecto proyectoDB = proyectoOptional.get();
            proyectoDB.setNombre(proyecto.getNombre());
            proyectoDB.setDescripcion(proyecto.getDescripcion());
            proyectoDB.setDuracionMeses(proyecto.getDuracionMeses());
            proyectoDB.setFechaInicio(proyecto.getFechaInicio());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(proyectoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Proyecto> proyectoOptional = service.findById(id);
        if (proyectoOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
