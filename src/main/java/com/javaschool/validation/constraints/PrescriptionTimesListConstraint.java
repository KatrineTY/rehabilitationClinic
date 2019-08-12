package com.javaschool.validation.constraints;

import com.javaschool.validation.validators.PrescriptionTimesListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PrescriptionTimesListValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrescriptionTimesListConstraint {
    String message() default "Time cannot be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
