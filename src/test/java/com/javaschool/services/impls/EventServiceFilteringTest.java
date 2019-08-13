package com.javaschool.services.impls;

import com.javaschool.dao.interfaces.EventDao;
import com.javaschool.entities.Event;
import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceFilteringTest {
    private static final List<Event> EVENTS_LIST;
    private static final int PAGE = 1;
    private static final int DB_PAGE = PAGE - 1;

    private static final LocalDate DATE = LocalDate.of(1990, 2, 5);
    private static final String NAME = "PATIENT";

    @Mock
    private EventDao eventDao;
    @InjectMocks
    private EventService eventService = new EventServiceImpl();

    static {
        EVENTS_LIST = Arrays.asList(
                Event.builder().patient(
                        Patient.builder().name("test patient").build()
                ).date(LocalDateTime.of(DATE, LocalTime.now())).build(),
                Event.builder().patient(
                        Patient.builder().name("patient").build()
                ).date(LocalDateTime.of(1990, 2, 6, 10, 20)).build(),
                Event.builder().patient(
                        Patient.builder().name("test").build()
                ).date(LocalDateTime.of(1990, 2, 7, 10, 20)).build()
        );
    }

    @BeforeEach
    void setup() {
        when(eventDao.getEvents()).thenReturn(EVENTS_LIST);
    }

    @Test
    void should_getAllEvents_whenFilteringParamsAreEmpty() {
        eventService.getFilteredEventsPage(PAGE, null, null);
        verify(eventDao).getEventsPage(DB_PAGE);
    }

    @Test
    void should_getFilteredEvents_whenFilteringByPatientName() {
        eventService.getFilteredEventsPage(PAGE, NAME, null);
        verify(eventDao).getFilteredEventsPage(DB_PAGE, NAME);
    }

    @Test
    void should_getFilteredEvents_whenFilteringByDate() {
        eventService.getFilteredEventsPage(PAGE, null, DATE);
        verify(eventDao).getFilteredEventsPage(DB_PAGE, DATE);
    }

    @Test
    void should_getFilteredEvents_whenFilteringByPatientNameAndDate() {
        eventService.getFilteredEventsPage(PAGE, NAME, DATE);
        verify(eventDao).getFilteredEventsPage(DB_PAGE, NAME, DATE);
    }

}
