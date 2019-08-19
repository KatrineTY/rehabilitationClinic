package com.javaschool.validation.constraints;

import com.javaschool.validation.validators.PrescriptionPatientDischargedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PrescriptionPatientDischargedValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrescriptionPatientDischargedConstraint {
    String message() default "Discharged patient cannot get any prescriptions";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
