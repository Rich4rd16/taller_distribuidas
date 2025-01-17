package com.proyecto.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaValidator implements ConstraintValidator<FechaValida, String> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String fecha, ConstraintValidatorContext context) {
        if (fecha == null || fecha.isEmpty()) {
            return false; // La fecha no puede ser nula o vacía
        }
        try {
            // Verifica que la fecha siga el formato "yyyy-MM-dd"
            LocalDate.parse(fecha, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false; // Si no se puede parsear, no es válida
        }
    }
}