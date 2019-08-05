package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PatientCardDao;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.PatientCardService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
@Transactional
public class PatientCardServiceImpl implements PatientCardService {
    @Autowired
    private PatientCardDao patientCardDao;

    @Override
    public List<PatientCard> getPatientCards() {
        return patientCardDao.getPatientCards();
    }

    @Override
    public void addPatientCard(PatientCard patientCard) {
        patientCardDao.addPatientCard(patientCard);
    }

    @Override
    public PatientCard getPatientCard(Patient patient) {
        return patientCardDao.getPatientCard(patient);
    }

    @Override
    public void updatePatientCard(PatientCard patientCard) {
        patientCardDao.updatePatientCard(patientCard);
    }

    @Override
    public void changeStatus(int patientId, String status) {
        patientCardDao.changeStatus(patientId, status);
    }

}
