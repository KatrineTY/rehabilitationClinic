package com.javaschool.validation.validators;

import com.javaschool.entities.PrescriptionTime;
import com.javaschool.validation.constraints.PrescriptionTimesListConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PrescriptionTimesListValidator implements ConstraintValidator<PrescriptionTimesListConstraint, List<PrescriptionTime>> {
    @Override
    public boolean isValid(List<PrescriptionTime> prescriptionTimes, ConstraintValidatorContext constraintValidatorContext) {
        return prescriptionTimes.stream().noneMatch(prescriptionTime -> prescriptionTime.getTime() == null);
    }

}
