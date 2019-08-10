package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PrescriptionDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.*;
import com.javaschool.services.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrescriptionServiceImplTest {
    private static final Prescription PRESCRIPTION;
    private static final List<PrescriptionTime> PRESCRIPTION_TIMES;
    private static final Patient PATIENT;
    private static final ProcedureAndMedicament PROMED;
    private static final Employee DOCTOR;
    private static final PrescriptionInfo PRESCRIPTION_INFO;
    private static final String DOCTOR_NAME = "doctor";
    private static final String PATIENT_NAME = "patient";

    static {
        DOCTOR = Employee.builder()
                .name(DOCTOR_NAME)
                .build();
        PATIENT = Patient.builder()
                .name(PATIENT_NAME)
                .build();
        PROMED = ProcedureAndMedicament.builder()
                .name("promed name")
                .build();
        PRESCRIPTION = Prescription.builder()
                .patient(PATIENT)
                .responsibleDoctor(DOCTOR)
                .type(PROMED)
                .build();
        PRESCRIPTION_TIMES = Arrays.asList(
                PrescriptionTime.builder()
                        .prescription(PRESCRIPTION)
                        .build(),
                PrescriptionTime.builder()
                        .prescription(PRESCRIPTION)
                        .build()
        );
        PRESCRIPTION_INFO = PrescriptionInfo.builder()
                .prescription(Prescription.builder()
                        .responsibleDoctor(Employee.builder().build())
                        .patient(Patient.builder().name(PATIENT_NAME).build())
                        .type(ProcedureAndMedicament.builder().build())
                        .build())
                .prescriptionTimes(
                        Arrays.asList(
                                PrescriptionTime.builder().build()
                                , PrescriptionTime.builder().build()))
                .build();
    }

    @Mock
    private PrescriptionTimeService prescriptionTimeService;
    @Mock
    private PrescriptionDao prescriptionDao;
    @Mock
    private EventService eventService;
    @Mock
    private PatientService patientService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private ProcedureAndMedicamentService procedureAndMedicamentService;


    @InjectMocks
    private PrescriptionService prescriptionService = new PrescriptionServiceImpl();

    @BeforeEach
    void setup() {
        tunePrescriptionDao();
        tunePrescriptionTimeService();
    }

    @Test
    void should_updatePrescriptionAndPrescriptionTime_whenUpdatePrescriptionInfo() {
        tuneEmployeeService();
        tunePatientService();
        tuneProcedureAndMedicamentServiceService();

        prescriptionService.updatePrescriptionInfo(PRESCRIPTION_INFO, DOCTOR_NAME);
        verify(prescriptionDao).updatePrescription(PRESCRIPTION);
        verify(prescriptionTimeService).updatePrescriptionTimes(PRESCRIPTION_TIMES);
    }

    @Test
    void should_returnFilledPrescriptionInfo_whenGetPrescription() {
        PrescriptionInfo actualPrescriptionInfo = prescriptionService.getPrescription(1);
        PrescriptionInfo expectedPrescriptionInfo = PrescriptionInfo.builder()
                .prescription(PRESCRIPTION)
                .prescriptionTimes(PRESCRIPTION_TIMES)
                .build();
        assertEquals(expectedPrescriptionInfo, actualPrescriptionInfo);

    }

    private void tunePatientService() {
        when(patientService.getPatient(PATIENT_NAME)).thenReturn(PATIENT);
    }

    private void tuneProcedureAndMedicamentServiceService() {
        when(procedureAndMedicamentService.getElementWithId(PRESCRIPTION_INFO.getPrescription().getType()))
                .thenReturn(PROMED);
    }

    private void tuneEmployeeService() {
        when(employeeService.getEmployeeByName(DOCTOR_NAME)).thenReturn(DOCTOR);
    }

    private void tunePrescriptionDao() {
        when(prescriptionDao.getPrescription(anyInt()))
                .thenReturn(PRESCRIPTION);
    }

    private void tunePrescriptionTimeService() {
        when(prescriptionTimeService.getPrescriptionTimes(PRESCRIPTION))
                .thenReturn(PRESCRIPTION_TIMES);
    }


}