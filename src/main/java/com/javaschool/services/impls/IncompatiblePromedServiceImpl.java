package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.IncompatiblePromedDao;
import com.javaschool.entities.IncompatibleProcedureAndMedicament;
import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.IncompatiblePromedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class IncompatiblePromedServiceImpl implements IncompatiblePromedService {
    @Autowired
    private IncompatiblePromedDao incompatiblePromedDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<IncompatibleProcedureAndMedicament> getIncompatiblePromeds(ProcedureAndMedicament promed) {
        return incompatiblePromedDao.getIncompatiblePromeds(promed);
    }
}
