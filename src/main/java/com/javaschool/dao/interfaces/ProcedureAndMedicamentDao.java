package com.javaschool.dao.interfaces;

import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface ProcedureAndMedicamentDao {

    /**
     * Retrieve procedure or medicament id by its data
     *
     * @param procedureAndMedicament - the item whose name you want to get
     * @return the specified id
     */
    ProcedureAndMedicament getElementWithId(ProcedureAndMedicament procedureAndMedicament);

    /**
     * Retrieve all procedures and medicines
     *
     * @return list of procedures and medicines
     */
    List<ProcedureAndMedicament> getProceduresAndMedicines();

}
