package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.DiagnosisDao;
import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.DiagnosisServise;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
@Transactional
public class DiagnosisServiceImpl implements DiagnosisServise {
    @Autowired
    private DiagnosisDao diagnosisDao;

    @Override
    public void addDiagnosis(Diagnosis diagnosis) {
        diagnosisDao.addDiagnosis(diagnosis);
    }

    @Override
    public List<Diagnosis> getDiagnoses(PatientCard patientCard) {
        return diagnosisDao.getDiagnoses(patientCard);
    }

    @Override
    public void updateDiagnosis(Diagnosis diagnosis) {
        diagnosisDao.updateDiagnosis(diagnosis);
    }

}
