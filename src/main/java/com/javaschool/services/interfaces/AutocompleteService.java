package com.javaschool.services.interfaces;

import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface AutocompleteService {

    /**
     * Retrieve all patient names
     *
     * @return list of patient names
     */
    List<String> getPatientNames();

    /**
     * Retrieve all procedures and medicines
     *
     * @return list of procedures and medicines
     */
    List<ProcedureAndMedicament> getProcedureAndMedicamentNames();

}
