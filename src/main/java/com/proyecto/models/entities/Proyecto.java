package com.proyecto.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;;

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
    @Min(value = 1, message = "La duración debe ser mayor a 0")
    @Max(value = 120, message = "La duración no puede exceder 120 meses")
    @Digits(integer = 3, fraction = 0,
            message = "La duración debe ser un número entero")
    private int duracionMeses;

    @Column(nullable = false)
    @NotNull(message = "La fecha de inicio no puede ser nula")
    @Future(message = "La fecha de inicio debe ser posterior a la fecha actual")
    private LocalDate fechaInicio;

    // Getters y Setters permanecen igual
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

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}