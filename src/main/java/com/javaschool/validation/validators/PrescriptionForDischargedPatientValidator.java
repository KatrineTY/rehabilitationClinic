package com.javaschool.validation.validators;

import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import com.javaschool.entities.Prescription;
import com.javaschool.services.interfaces.PatientCardService;
import com.javaschool.services.interfaces.PatientService;
import com.javaschool.validation.constraints.PrescriptionForDischargedPatientConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PrescriptionForDischargedPatientValidator implements ConstraintValidator<PrescriptionForDischargedPatientConstraint, Prescription> {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientCardService patientCardService;

    @Override
    public boolean isValid(Prescription prescription, ConstraintValidatorContext constraintValidatorContext) {
        if (prescription.getPatient().getName().isEmpty()) {
            return false;
        }
        Patient patient = patientService.getPatient(prescription.getPatient().getName());
        if (patient == null) {
            return false;
        }
        PatientCard patientCard = patientCardService.getPatientCard(patient);
        boolean isValid = !patientCard.getStatus().equals("Discharged");
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Discharged patient cannot get any prescriptions")
                    .addConstraintViolation();
        }
        return isValid;
    }

}
