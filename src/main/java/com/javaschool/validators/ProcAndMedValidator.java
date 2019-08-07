package com.javaschool.validators;

import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.ProcedureAndMedicamentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProcAndMedValidator
        implements ConstraintValidator<ProcAndMedConstraint, ProcedureAndMedicament> {
    @Autowired
    private ProcedureAndMedicamentService procedureAndMedicamentService;

    @Override
    public boolean isValid(ProcedureAndMedicament procedureAndMedicament,
                           ConstraintValidatorContext constraintValidatorContext) {
        return !procedureAndMedicament.getKind().equals("Medicament")
                || procedureAndMedicamentService.getMedicines().contains(procedureAndMedicament.getName());
    }

}
