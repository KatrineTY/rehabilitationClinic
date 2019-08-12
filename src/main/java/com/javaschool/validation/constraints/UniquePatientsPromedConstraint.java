package com.javaschool.validation.constraints;

import com.javaschool.validation.validators.UniquePatientsPromedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePatientsPromedValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePatientsPromedConstraint {
    String message() default "Prescription with this promed already exists in this period of time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
