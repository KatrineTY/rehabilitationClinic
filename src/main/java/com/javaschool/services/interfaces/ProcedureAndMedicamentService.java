package com.javaschool.services.interfaces;

import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface ProcedureAndMedicamentService {

    /**
     * Retrieve procedure or medicament id by its data
     *
     * @param procedureAndMedicament - the item whose name you want to get
     * @return the specified id
     */
    ProcedureAndMedicament getPromedWithId(ProcedureAndMedicament procedureAndMedicament);

    /**
     * Retrieve all procedures and medicines
     *
     * @return list of procedures and medicines
     */
    List<ProcedureAndMedicament> getProceduresAndMedicines();

    /**
     * Retrieve all medicines
     *
     * @return list of medicines
     */
    List<String> getMedicines();

    /**
     * Retrieve all procedures
     *
     * @return list of procedures
     */
    List<String> getProcedures();

}
