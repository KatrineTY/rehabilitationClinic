package com.javaschool.validation.validators;

import com.javaschool.entities.Prescription;
import com.javaschool.services.interfaces.PrescriptionService;
import com.javaschool.validation.constraints.UniquePatientsPromedConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePatientsPromedValidator implements ConstraintValidator<UniquePatientsPromedConstraint, Prescription> {
    @Autowired
    private PrescriptionService prescriptionService;

    @Override
    public boolean isValid(Prescription prescription, ConstraintValidatorContext constraintValidatorContext) {
        Prescription lastPrescriptionWithCurrentPromed = prescriptionService
                .getLastPrescription(prescription.getPatient().getName(), prescription.getType());
        boolean isValid = lastPrescriptionWithCurrentPromed.getEndDate().isBefore(prescription.getStartDate());
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(".type.kind").addConstraintViolation();
        }
        return isValid;
    }

}
