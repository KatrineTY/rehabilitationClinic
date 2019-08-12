package com.javaschool.validation.validators;

import com.javaschool.services.interfaces.PatientService;
import com.javaschool.validation.constraints.UniqueInsuranceConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueInsuranceValidator implements ConstraintValidator<UniqueInsuranceConstraint, String> {
    @Autowired
    private PatientService patientService;

    @Override
    public boolean isValid(String insurance, ConstraintValidatorContext constraintValidatorContext) {
        return !insurance.matches("[A-Z0-9 ]+") || !patientService.isInsuranceContains(insurance);
    }

}
