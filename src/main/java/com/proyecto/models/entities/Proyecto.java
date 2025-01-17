package com.proyecto.models.entities;

import com.proyecto.validators.FechaValida;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "Proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
            message = "El nombre solo puede contener letras y espacios")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "La descripción no puede estar vacía")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ.,\\s]+$",
            message = "La descripción solo puede contener letras, números y signos básicos de puntuación")
    private String descripcion;

    @Column(nullable = false)
    @NotNull(message = "La duración no puede estar vacía")
    @Min(value = 1, message = "La duración debe ser mayor a 0")
    @Max(value = 120, message = "La duración no puede exceder 120 meses")
    @Pattern(regexp = "^[0-9]+$", message = "La duración solo puede contener números")
    private String duracionMeses; // Cambiado a String para validar el formato

    @Column(nullable = false)
    @NotNull(message = "La fecha de inicio no puede ser nula")
    @FechaValida
    private String fechaInicio;

    // Getters y Setters actualizados
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(String duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // Método auxiliar para obtener la duración como entero
    public int getDuracionMesesAsInt() {
        try {
            return Integer.parseInt(duracionMeses);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}