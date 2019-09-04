package com.javaschool.validation.validators;

import com.javaschool.entities.Prescription;
import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.IncompatiblePromedService;
import com.javaschool.services.interfaces.PrescriptionService;
import com.javaschool.validation.constraints.IncompatiblePromedConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IncompatiblePromedValidator implements ConstraintValidator<IncompatiblePromedConstraint, Prescription> {

    @Autowired
    private IncompatiblePromedService incompatiblePromedService;
    @Autowired
    private PrescriptionService prescriptionService;

    @Override
    public boolean isValid(Prescription prescription, ConstraintValidatorContext constraintValidatorContext) {
        if (prescription.getPatient().getName().isEmpty() || prescription.getType().getName().isEmpty()) {
            return true;
        }
        List<ProcedureAndMedicament> currentPromeds = prescriptionService.getPrescriptions(prescription.getPatient().getName()).stream()
                .map(Prescription::getType).collect(Collectors.toList());
        List<ProcedureAndMedicament> incompatiblePromeds = new ArrayList<>();
        currentPromeds.forEach(promed -> incompatiblePromeds.addAll(
                incompatiblePromedService.getIncompatiblePromeds(promed).stream()
                        .map(incompPromed -> incompPromed.getPromed().equals(promed) ?
                                incompPromed.getIncompatiblePromed() : incompPromed.getPromed())
                        .collect(Collectors.toList())));

        boolean isValid = incompatiblePromeds.stream()
                .noneMatch(promed -> promed.getName().equals(prescription.getType().getName()));
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("This procedure / medication should not be used with an existing one.")
                    .addPropertyNode("type").addConstraintViolation();
        }
        return isValid;
    }
}
