package com.javaschool.validators;

import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

public class PatientNameValidator implements ConstraintValidator<PatientNameConstraint, String> {
    @Autowired
    private PatientService patientService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return patientService
                .getPatients()
                .stream()
                .map(Patient::getName)
                .collect(Collectors.toList()).contains(name);
    }

}
