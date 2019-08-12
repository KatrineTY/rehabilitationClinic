package com.javaschool.validation.validators;

import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.PatientService;
import com.javaschool.validation.constraints.PatientNameConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

public class PatientNameValidator implements ConstraintValidator<PatientNameConstraint, String> {
    @Autowired
    private PatientService patientService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return name.isEmpty() || patientService
                .getPatients()
                .stream()
                .map(Patient::getName)
                .collect(Collectors.toList()).contains(name);
    }

}
