package com.javaschool.validation.constraints;


import com.javaschool.validation.validators.PatientNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PatientNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PatientNameConstraint {
    String message() default "Invalid patient name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
