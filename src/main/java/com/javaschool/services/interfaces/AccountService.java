package com.javaschool.services.interfaces;

import com.javaschool.dao.objects.Patient;

import java.util.List;

public interface AccountService {

    List<Patient> getPatients();

}
