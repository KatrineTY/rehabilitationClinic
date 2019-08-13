package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.PatientDao;
import com.javaschool.dto.PatientInfo;
import com.javaschool.entities.Diagnosis;
import com.javaschool.entities.Employee;
import com.javaschool.entities.Patient;
import com.javaschool.entities.PatientCard;
import com.javaschool.services.interfaces.DiagnosisService;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.PatientCardService;
import com.javaschool.services.interfaces.PatientService;
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
class PatientServiceImplTest {
    private static final String DOCTOR_NAME = "patient";
    private static final PatientInfo PATIENT_INFO;
    private static final PatientCard PATIENT_CARD;
    private static final List<Diagnosis> DIAGNOSES;
    private static final Employee DOCTOR;

    @Mock
    private PatientDao patientDao;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private PatientCardService patientCardService;
    @Mock
    private DiagnosisService diagnosisService;
    @InjectMocks
    private PatientService patientService = new PatientServiceImpl();

    static {
        Patient patient = Patient.builder().name("patient").build();
        DOCTOR = Employee.builder()
                .name(DOCTOR_NAME)
                .id(5)
                .build();

        PATIENT_CARD = PatientCard.builder()
                .attendingDoctor(DOCTOR)
                .patient(patient)
                .build();
        Diagnosis diagnosis1 = Diagnosis.builder()
                .patientCard(PATIENT_CARD)
                .build();
        Diagnosis diagnosis2 = Diagnosis.builder()
                .patientCard(PATIENT_CARD)
                .build();
        DIAGNOSES = Arrays.asList(diagnosis1, diagnosis2);

        PATIENT_INFO = PatientInfo.builder()
                .patient(patient)
                .patientCard(PatientCard.builder()
                        .attendingDoctor(Employee.builder()
                                .name(DOCTOR_NAME)
                                .build())
                        .build())
                .diagnoses(Arrays.asList(Diagnosis.builder().build(), Diagnosis.builder().build()))
                .build();

    }

    @Test
    void addPatient() {
        when(employeeService.getEmployeeByName(DOCTOR_NAME)).thenReturn(DOCTOR);
        patientService.addPatient(PATIENT_INFO);
        verify(patientDao).addPatient(PATIENT_INFO.getPatient());
        verify(patientCardService).addPatientCard(PATIENT_CARD);
        ArgumentCaptor<Diagnosis> diagnosisCaptor = ArgumentCaptor.forClass(Diagnosis.class);
        verify(diagnosisService, times(2)).addDiagnosis(diagnosisCaptor.capture());
        assertEquals(DIAGNOSES, diagnosisCaptor.getAllValues());
    }

}