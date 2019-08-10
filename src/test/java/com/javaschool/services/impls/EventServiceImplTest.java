package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.*;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
    private static final PrescriptionInfo PRESCRIPTION_INFO;
    private static final List<Event> EVENTS;
    private static final Employee NURSE;
    private static final String COMMENT = "Comment";
    private static final String REJECTED_STATUS = "Rejected";

    static {
        Patient patient = Patient.builder()
                .name("patient")
                .build();
        Prescription prescription = Prescription.builder()
                .patient(patient)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
        List<PrescriptionTime> prescriptionTimes = Arrays.asList(
                PrescriptionTime.builder()
                        .prescription(prescription)
                        .time(LocalTime.of(10, 30))
                        .build(),
                PrescriptionTime.builder()
                        .prescription(prescription)
                        .time(LocalTime.of(15, 30))
                        .build());
        prescription.setPrescriptionTimes(prescriptionTimes);
        PRESCRIPTION_INFO = PrescriptionInfo.builder()
                .prescription(prescription)
                .prescriptionTimes(prescriptionTimes)
                .build();

        EVENTS = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            EVENTS.add(Event.builder()
                    .patient(patient)
                    .date(LocalDateTime.of(LocalDate.now().plusDays(i), LocalTime.of(10, 30)))
                    .status("Planned")
                    .build());
        }
        for (int i = 0; i < 3; i++) {
            EVENTS.add(Event.builder()
                    .patient(patient)
                    .date(LocalDateTime.of(LocalDate.now().plusDays(i), LocalTime.of(15, 30)))
                    .status("Planned")
                    .build());
        }

        NURSE = Employee.builder()
                .name("Claret")
                .build();
    }

    @Mock
    private EventDao eventDao;
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EventService eventService = new EventServiceImpl();

    @Test
    void should_addSixEvents_forPrescriptionWithThreeDaysAndTwoTimes() {
        eventService.addEvents(PRESCRIPTION_INFO);
        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventDao, times(6)).addEvent(eventCaptor.capture());
        assertEquals(EVENTS, eventCaptor.getAllValues());
    }

    @Test
    void should_deleteSixEvents_forPrescriptionWithThreeDaysAndTwoTimes() {
        tuneEventDao();
        eventService.deleteEvents(PRESCRIPTION_INFO);
        verify(eventDao).getEvents(PRESCRIPTION_INFO);
        verify(eventDao, times(6)).deleteEvent(any());
    }

    @Test
    void should_updateEventStatusWithSpecifiedNurse_whenRejectTask() {
        tuneEmployeeService();
        eventService.rejectTask(1, NURSE.getName(), COMMENT);
        verify(eventDao).updateEventStatus(1, NURSE, COMMENT, REJECTED_STATUS);
    }

    private void tuneEmployeeService() {
        when(employeeService.getEmployeeByName(NURSE.getName())).thenReturn(NURSE);
    }

    private void tuneEventDao() {
        when(eventDao.getEvents(PRESCRIPTION_INFO)).thenReturn(EVENTS);
    }

}