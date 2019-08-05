package com.javaschool.dao.interfaces;

import com.javaschool.entities.Prescription;

import java.util.List;

public interface PrescriptionDao {

    void addPrescription(Prescription prescription);

    List<Prescription> getPrescriptions();

    Prescription getPrescription(int id);

    void updatePrescription(Prescription prescription);

    void deletePrescription(int id);

    void deletePrescriptions(int patientId);

}
