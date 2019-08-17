package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.ProcedureAndMedicamentDao;
import com.javaschool.entities.ProcedureAndMedicament;
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
public class ProcedureAndMedicamentServiceImpl implements ProcedureAndMedicamentService {
    @Autowired
    private ProcedureAndMedicamentDao procedureAndMedicamentDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcedureAndMedicament getPromedWithId(ProcedureAndMedicament procedureAndMedicament) {
        return procedureAndMedicamentDao.getElementWithId(procedureAndMedicament);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProcedureAndMedicament> getProceduresAndMedicines() {
        return procedureAndMedicamentDao.getProceduresAndMedicines();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getMedicines() {
        return getProceduresAndMedicines()
                .stream()
                .filter(x -> x.getKind().equals("Medicament"))
                .map(ProcedureAndMedicament::getName)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getProcedures() {
        return getProceduresAndMedicines()
                .stream()
                .filter(x -> x.getKind().equals("Procedure"))
                .map(ProcedureAndMedicament::getName)
                .collect(Collectors.toList());
    }

}
