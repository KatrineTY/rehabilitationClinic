package com.javaschool.services.impls;

import com.javaschool.entities.Patient;
import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.AutocompleteService;
import com.javaschool.services.interfaces.PatientService;
import com.javaschool.services.interfaces.ProcedureAndMedicamentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Transactional
public class AutocompleteServiceImpl implements AutocompleteService {
    @Autowired
    private PatientService patientService;
    @Autowired
    private ProcedureAndMedicamentService procedureAndMedicamentService;

    @Override
    public List<String> getPatientNames() {
        return patientService.getPatients().stream().map(Patient::getName).collect(Collectors.toList());
    }

    @Override
    public List<ProcedureAndMedicament> getProcedureAndMedicamentNames() {
        return procedureAndMedicamentService.getProceduresAndMedicines();
    }

}
