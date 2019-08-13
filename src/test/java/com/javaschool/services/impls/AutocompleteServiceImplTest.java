package com.javaschool.services.impls;

import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.AutocompleteService;
import com.javaschool.services.interfaces.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutocompleteServiceImplTest {
    private static final List<Patient> PATIENTS_LIST = Arrays.asList(
            Patient.builder().name("first name").build(),
            Patient.builder().name("second name").build()
    );

    @Mock
    private PatientService patientService;
    @InjectMocks
    private AutocompleteService autocompleteService = new AutocompleteServiceImpl();

    @Test
    void should_returnPatientNames_whenServiceGetPatientNames() {
        when(patientService.getPatients()).thenReturn(PATIENTS_LIST);
        List<String> actualPatientNames = autocompleteService.getPatientNames();
        assertEquals(PATIENTS_LIST.stream().map(Patient::getName).collect(Collectors.toList()), actualPatientNames);
    }

    @Test
    void should_returnEmptyList_whenThereIsNoPatients() {
        when(patientService.getPatients()).thenReturn(new ArrayList<>());
        List<String> actualPatientNames = autocompleteService.getPatientNames();
        assertTrue(actualPatientNames.isEmpty());
    }

}