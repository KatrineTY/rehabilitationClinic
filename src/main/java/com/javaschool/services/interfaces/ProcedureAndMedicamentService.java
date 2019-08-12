package com.javaschool.services.interfaces;

import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface ProcedureAndMedicamentService {

    ProcedureAndMedicament getElementWithId(ProcedureAndMedicament procedureAndMedicament);

    List<ProcedureAndMedicament> getProceduresAndMedicines();

    List<String> getMedicines();

    List<String> getProcedures();

}
