package com.javaschool.validation.constraints;

import com.javaschool.validation.validators.UniqueInsuranceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueInsuranceValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueInsuranceConstraint {
    String message() default "Patient with this insurance already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
