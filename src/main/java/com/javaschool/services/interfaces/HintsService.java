package com.javaschool.services.interfaces;

import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface HintsService {
    List<String> getPatientNames();

    List<String> getMedicamentNames();

    List<ProcedureAndMedicament> getProcedureNames();

}
