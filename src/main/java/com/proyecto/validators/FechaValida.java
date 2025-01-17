package com.proyecto.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FechaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaValida {
    String message() default "La fecha debe tener el formato YYYY-MM-DD y solo puede contener números";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}