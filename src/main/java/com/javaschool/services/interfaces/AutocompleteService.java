package com.javaschool.services.interfaces;

import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface AutocompleteService {
    List<String> getPatientNames();

    List<ProcedureAndMedicament> getProcedureAndMedicamentNames();

}
