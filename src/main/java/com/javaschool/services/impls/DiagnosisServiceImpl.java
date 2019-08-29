package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.DiagnosisDao;
import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.DiagnosisService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    @Autowired
    private DiagnosisDao diagnosisDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addDiagnosis(Diagnosis diagnosis) {
        diagnosisDao.addDiagnosis(diagnosis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Diagnosis> getDiagnoses(PatientCard patientCard) {
        return diagnosisDao.getDiagnoses(patientCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveDiagnosis(Diagnosis diagnosis) {
        diagnosisDao.saveDiagnosis(diagnosis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteDiagnoses(PatientCard patientCard) {
        diagnosisDao.deleteDiagnoses(patientCard);
    }

}
