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
        boolean isValid;
        if (prescription.getPatient().getName().isEmpty() || prescription.getType().getName().isEmpty()
                || prescription.getEndDate() == null || prescription.getStartDate() == null) {
            isValid = false;
        } else {
            Prescription lastPrescriptionWithCurrentPromed = prescriptionService
                    .getLastPrescription(prescription.getPatient().getName(), prescription.getType());
            isValid = lastPrescriptionWithCurrentPromed == null ||
                    lastPrescriptionWithCurrentPromed.getEndDate().isBefore(prescription.getStartDate());
            if (!isValid) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Prescription with this promed already exists in this period of time")
                        .addPropertyNode("type.kind").addConstraintViolation();
            }
        }
        return isValid;
    }

}
