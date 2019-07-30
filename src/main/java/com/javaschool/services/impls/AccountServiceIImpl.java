package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PatientCardDao;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.AccountService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@NoArgsConstructor
@Transactional
public class AccountServiceIImpl implements AccountService {
    @Autowired
    private PatientCardDao patientCardDao;

    @Override
    public List<PatientCard> getPatientCards() {
        return patientCardDao.getPatientCards();
    }

}
