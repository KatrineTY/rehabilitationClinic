package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.entities.*;
import com.javaschool.services.interfaces.EmployeeService;
import com.javaschool.services.interfaces.EventService;
import com.javaschool.services.interfaces.PatientCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
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
    // for filters
    private static final List<Event> EVENTS_LIST;
    private static final PatientCard PATIENT_CARD;
    private static final int PAGE = 1;
    private static final int DB_PAGE = PAGE - 1;
    private static final LocalDate DATE = LocalDate.of(1990, 2, 5);
    private static final LocalTime TIME = LocalTime.of(10, 29);
    private static final String NAME = "PATIENT";
    private static final TimePeriodInfo TIME_PERIOD_DEFAULT;
    private static final TimePeriodInfo TIME_PERIOD;


    static {
        PATIENT_CARD = PatientCard.builder()
                .building("C")
                .ward(5)
                .build();
        Patient patient = Patient.builder()
                .name("patient")
                .build();
        Prescription prescription = Prescription.builder()
                .patient(patient)
                .startDate(LocalDate.of(2019, 8, 19))
                .endDate(LocalDate.of(2019, 8, 19).plusDays(3))
                .prescriptionDays(Collections.singletonList("TUESDAY"))

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

        EVENTS = Arrays.asList(Event.builder()
                        .patient(patient)
                        .date(LocalDateTime.of(LocalDate.of(2019, 8, 20), LocalTime.of(10, 30)))
                        .status("Planned")
                        .ward(PATIENT_CARD.getWard())
                        .building(PATIENT_CARD.getBuilding())
                        .build(),
                Event.builder()
                        .patient(patient)
                        .date(LocalDateTime.of(LocalDate.of(2019, 8, 20), LocalTime.of(15, 30)))
                        .status("Planned")
                        .ward(PATIENT_CARD.getWard())
                        .building(PATIENT_CARD.getBuilding())
                        .build());
        NURSE = Employee.builder()
                .name("Claret")
                .build();

        EVENTS_LIST = Arrays.asList(
                Event.builder().patient(Patient.builder().name("test patient").build())
                        .date(LocalDateTime.of(DATE, LocalTime.now()))
                        .build(),
                Event.builder().patient(Patient.builder().name("patient").build())
                        .date(LocalDateTime.of(1990, 2, 6, 10, 20))
                        .build(),
                Event.builder().patient(Patient.builder().name("test").build())
                        .date(LocalDateTime.of(1990, 2, 7, 10, 20))
                        .build()
        );

        TIME_PERIOD_DEFAULT = new TimePeriodInfo();
        TIME_PERIOD = new TimePeriodInfo();
        TIME_PERIOD.setEndTime(TIME);

    }

    @Mock
    private EventDao eventDao;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private PatientCardService patientCardService;
    @InjectMocks
    private EventService eventService = new EventServiceImpl();

    @Test
    void should_addTwoEvent_forPrescriptionWithThreeDaysAndTwoTimes() {
        when(patientCardService.getPatientCard(PRESCRIPTION_INFO.getPrescription().getPatient())).thenReturn(PATIENT_CARD);
        eventService.addEvents(PRESCRIPTION_INFO);
        ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventDao, times(2)).addEvent(eventCaptor.capture());
        assertEquals(EVENTS, eventCaptor.getAllValues());
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

    // filters
    @Test
    void should_getAllEvents_whenFilteringParamsAreEmpty() {
        when(eventDao.getEvents()).thenReturn(EVENTS_LIST);
        eventService.getFilteredEventsPage(PAGE, null, null, TIME_PERIOD_DEFAULT);
        verify(eventDao).getEventsPage(DB_PAGE);
    }

    @Test
    void should_getFilteredEvents_whenFilteringByPatientName() {
        when(eventDao.getEvents()).thenReturn(EVENTS_LIST);
        eventService.getFilteredEventsPage(PAGE, NAME, null, TIME_PERIOD_DEFAULT);
        verify(eventDao).getFilteredEventsPage(DB_PAGE, NAME, TIME_PERIOD_DEFAULT);
    }

    @Test
    void should_getFilteredEvents_whenFilteringByDate() {
        when(eventDao.getEvents()).thenReturn(EVENTS_LIST);
        eventService.getFilteredEventsPage(PAGE, null, DATE, TIME_PERIOD_DEFAULT);
        verify(eventDao).getFilteredEventsPage(DB_PAGE, DATE, TIME_PERIOD_DEFAULT);
    }

    @Test
    void should_getFilteredEvents_whenFilteringByPatientNameAndDate() {
        when(eventDao.getEvents()).thenReturn(EVENTS_LIST);
        eventService.getFilteredEventsPage(PAGE, NAME, DATE, TIME_PERIOD_DEFAULT);
        verify(eventDao).getFilteredEventsPage(DB_PAGE, NAME, DATE, TIME_PERIOD_DEFAULT);
    }

    @Test
    void should_getFilteredEvents_whenFilteringByTime() {
        when(eventDao.getEvents()).thenReturn(EVENTS_LIST);
        eventService.getFilteredEventsPage(PAGE, null, null, TIME_PERIOD);
        verify(eventDao).getFilteredEventsPage(DB_PAGE, TIME_PERIOD);
    }

}