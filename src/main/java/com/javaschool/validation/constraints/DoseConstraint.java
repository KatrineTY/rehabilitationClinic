package com.javaschool.validation.constraints;

import com.javaschool.validation.validators.DoseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DoseValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DoseConstraint {
    String message() default "Wrong dose format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
