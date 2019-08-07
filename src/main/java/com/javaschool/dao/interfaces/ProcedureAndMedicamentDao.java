package com.javaschool.dao.interfaces;

import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface ProcedureAndMedicamentDao {

    ProcedureAndMedicament getElementWithId(ProcedureAndMedicament procedureAndMedicament);

    List<ProcedureAndMedicament> getProceduresAndMedicines();

}
