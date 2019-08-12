package com.javaschool.validation.validators;

import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.PatientCardService;
import com.javaschool.validation.constraints.PatientCountInWardConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatientCountInWardValidator implements ConstraintValidator<PatientCountInWardConstraint, PatientCard> {
    @Autowired
    private PatientCardService patientCardService;

    @Override
    public boolean isValid(PatientCard patientCard, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = patientCardService.isFreeBedInTheWard(patientCard.getBuilding(), patientCard.getWard());
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("ward").addConstraintViolation();
        }
        return isValid;
    }

}
