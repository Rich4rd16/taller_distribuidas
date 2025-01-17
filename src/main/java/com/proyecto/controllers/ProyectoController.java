package com.proyecto.controllers;

import com.proyecto.dto.ErrorResponse;
import com.proyecto.dto.ValidationError;
import com.proyecto.models.entities.Proyecto;
import com.proyecto.services.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Proyecto proyecto, BindingResult result) {
        if(result.hasErrors()) {
            return validar(result);
        }
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
    public ResponseEntity<?> editar(@Valid @RequestBody Proyecto proyecto, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()) {
            return validar(result);
        }

        Optional<Proyecto> proyectoOptional = service.findById(id);
        if (proyectoOptional.isPresent()) {
            Proyecto proyectoDB = proyectoOptional.get();
            proyectoDB.setNombre(proyecto.getNombre());
            proyectoDB.setDescripcion(proyecto.getDescripcion());
            proyectoDB.setDuracionMeses(proyecto.getDuracionMeses());

            // Asegurarse de que la fecha no sea nula antes de asignarla
            if (proyecto.getFechaInicio() != null) {
                proyectoDB.setFechaInicio(proyecto.getFechaInicio());
            }

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

    private ResponseEntity<?> validar(BindingResult result) {
        List<ValidationError> errors = result.getFieldErrors()
                .stream()
                .map(err -> new ValidationError(
                        err.getField(),
                        err.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
                "Validation failed",
                errors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}