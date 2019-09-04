package com.javaschool.services.interfaces;

import com.javaschool.entities.IncompatibleProcedureAndMedicament;
import com.javaschool.entities.ProcedureAndMedicament;

import java.util.List;

public interface IncompatiblePromedService {

    /**
     * Retrieve incompatible to a specific procedure/medicament procedures/medicines
     *
     * @param promed - the specified procedure/medicament
     * @return the specified procedures/medicines
     */
    List<IncompatibleProcedureAndMedicament> getIncompatiblePromeds(ProcedureAndMedicament promed);

}
