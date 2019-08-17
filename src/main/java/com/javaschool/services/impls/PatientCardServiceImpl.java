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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PatientCard> getPatientCards() {
        return patientCardDao.getPatientCards();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPatientCard(PatientCard patientCard) {
        patientCardDao.addPatientCard(patientCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PatientCard getPatientCard(Patient patient) {
        return patientCardDao.getPatientCard(patient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePatientCard(PatientCard patientCard) {
        patientCardDao.updatePatientCard(patientCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStatus(int patientId, String status) {
        patientCardDao.changeStatus(patientId, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFreeBedInTheWard(String building, int ward) {
        return patientCardDao.isFreeBedInTheWard(building, ward);
    }

}
