package com.javaschool.dao.interfaces;

import com.javaschool.entities.Prescription;

import java.util.List;

public interface PrescriptionDao {

    void addPrescription(Prescription prescription);

    List<Prescription> getAllPrescriptions();

    Prescription getPrescriptionById(int id);

    void updatePrescription(Prescription prescription);

    void deletePrescriptionById(int id);

    void deletePrescriptionsByPatientId(int id);

    List<Integer> getPrescriptionsIdByPatientId(int id);

}
