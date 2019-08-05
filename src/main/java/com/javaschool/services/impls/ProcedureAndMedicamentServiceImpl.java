package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.ProcedureAndMedicamentDao;
import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.ProcedureAndMedicamentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@NoArgsConstructor
@Transactional
public class ProcedureAndMedicamentServiceImpl implements ProcedureAndMedicamentService {
    @Autowired
    private ProcedureAndMedicamentDao procedureAndMedicamentDao;

    @Override
    public ProcedureAndMedicament getElementWithId(ProcedureAndMedicament procedureAndMedicament) {
        return procedureAndMedicamentDao.getElementWithId(procedureAndMedicament);
    }

}
