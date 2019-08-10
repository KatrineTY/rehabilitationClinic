package com.javaschool.services.impls;

import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientManipulationServiceImplTest {
    private static final Patient PATIENT;
    private static final PatientCard PATIENT_CARD;
    private static final List<Diagnosis> DIAGNOSES;
    private static final PatientInfo PATIENT_INFO;
    private static final Employee DOCTOR;
    private static final String DOCTOR_NAME = "doctor";

    static {
        DOCTOR = Employee.builder()
                .name(DOCTOR_NAME)
                .id(1)
                .build();
        PATIENT = Patient.builder()
                .name("patient name")
                .build();
        PATIENT_CARD = PatientCard.builder()
                .attendingDoctor(DOCTOR)
                .patient(PATIENT)
                .build();
        Diagnosis diagnosis1 = Diagnosis.builder()
                .patientCard(PATIENT_CARD)
                .build();
        Diagnosis diagnosis2 = Diagnosis.builder()
                .patientCard(PATIENT_CARD)
                .build();
        DIAGNOSES = Arrays.asList(diagnosis1, diagnosis2);

        PATIENT_INFO = PatientInfo.builder()
                .patient(PATIENT)
                .patientCard(PatientCard.builder()
                        .attendingDoctor(Employee.builder()
                                .name(DOCTOR_NAME)
                                .build())
                        .build())
                .diagnoses(Arrays.asList(Diagnosis.builder().build(), Diagnosis.builder().build()))
                .build();
    }

    @Mock
    private PatientService patientService;
    @Mock
    private PatientCardService patientCardService;
    @Mock
    private DiagnosisService diagnosisService;
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private PatientManipulationService patientManipulationService = new PatientManipulationServiceImpl();

    @Test
    void should_returnFilledPatientInfo_whenGetPatientId() {
        tunePatientService();
        tuneCardService();
        tuneDiagnosisService();
        PatientInfo actualPatientInfo = patientManipulationService.getPatientInfo(1);
        PatientInfo expectedPatientInfo = PatientInfo.builder()
                .patient(PATIENT)
                .patientCard(PATIENT_CARD)
                .diagnoses(DIAGNOSES)
                .build();
        assertEquals(expectedPatientInfo, actualPatientInfo);
    }

    @Test
    void should_allPatientInfoForeignComponentsFill_whenUpdatePatientInfo() {
        tuneEmployeeService();
        patientManipulationService.updatePatientInfo(PATIENT_INFO);
        verify(patientService).updatePatient(PATIENT);
        verify(patientCardService).updatePatientCard(PATIENT_CARD);
        ArgumentCaptor<Diagnosis> diagnosisCaptor = ArgumentCaptor.forClass(Diagnosis.class);
        verify(diagnosisService, times(2)).saveOrUpdateDiagnosis(diagnosisCaptor.capture());
        assertEquals(DIAGNOSES, diagnosisCaptor.getAllValues());
    }

    private void tunePatientService() {
        when(patientService.getPatient(1)).thenReturn(PATIENT);
    }

    private void tuneCardService() {
        when(patientCardService.getPatientCard(PATIENT)).thenReturn(PATIENT_CARD);
    }

    private void tuneDiagnosisService() {
        when(diagnosisService.getDiagnoses(PATIENT_CARD)).thenReturn(DIAGNOSES);
    }

    private void tuneEmployeeService() {
        when(employeeService.getEmployeeByName(DOCTOR_NAME)).thenReturn(DOCTOR);
    }

}