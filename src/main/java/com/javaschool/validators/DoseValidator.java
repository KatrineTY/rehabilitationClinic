package com.javaschool.validators;

import com.javaschool.entities.Prescription;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoseValidator implements ConstraintValidator<DoseConstraint, Prescription> {

    @Override
    public boolean isValid(Prescription prescription, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = !prescription.getType().getKind().equals("Medicament")
                || prescription.getDose().matches("\\d+mg|\\d+ml");

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Wrong dose")
                    .addPropertyNode("dose").addConstraintViolation();
        }

        return isValid;
    }

}
