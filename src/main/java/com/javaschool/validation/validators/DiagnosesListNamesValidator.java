package com.javaschool.validation.validators;

import com.javaschool.entities.Diagnosis;
import com.javaschool.validation.constraints.DiagnosesListNamesConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DiagnosesListNamesValidator implements ConstraintValidator<DiagnosesListNamesConstraint, List<Diagnosis>> {
    @Override
    public boolean isValid(List<Diagnosis> diagnoses, ConstraintValidatorContext constraintValidatorContext) {
        return diagnoses.stream().noneMatch(diagnosis -> diagnosis.getName().trim().isEmpty());
    }

}
