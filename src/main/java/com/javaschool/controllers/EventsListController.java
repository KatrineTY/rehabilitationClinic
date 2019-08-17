package com.javaschool.controllers;

import com.javaschool.dto.TimePeriodInfo;
import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@NoArgsConstructor
@Slf4j
public class EventsListController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "get-events-list/take-task", method = RequestMethod.POST)
    public ModelAndView takeTask(@RequestParam(name = "event.id") int id,
                                 @SessionAttribute(name = "empName") String empName) {
        log.info("attempted to take event with id: {}", id);
        ModelAndView model = new ModelAndView();
        eventService.takeTask(id, empName);
        log.info("took event with id: {}", id);
        model.setViewName("redirect:../get-events-list/1");
        return model;
    }

    @RequestMapping(value = "get-events-list/reject-task", method = RequestMethod.POST)
    public ModelAndView rejectTask(@RequestParam(name = "event.id") int id,
                                   @RequestParam(name = "comment") String comment,
                                   @SessionAttribute(name = "empName") String empName) {
        log.info("attempted to reject event with id: {}", id);
        ModelAndView model = new ModelAndView();
        eventService.rejectTask(id, empName, comment);
        log.info("rejected event with id: {}", id);
        model.setViewName("redirect:../get-events-list/1");
        return model;
    }

    @RequestMapping(value = "get-events-list/{page}")
    public ModelAndView getEventList(@PathVariable int page,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam(name = "searchDate", required = false) LocalDate date,
                                     @RequestParam(name = "searchPatientName", required = false) String patientName,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                     @RequestParam(name = "searchStartTime", required = false) LocalTime startTime,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                     @RequestParam(name = "searchEndTime", required = false) LocalTime endTime) {
        log.info("requested events list");
        ModelAndView model = new ModelAndView("eventsList");
        TimePeriodInfo timePeriodInfo = new TimePeriodInfo();
        if (startTime != null) {
            timePeriodInfo.setStartTime(startTime);
        }
        if (endTime != null) {
            timePeriodInfo.setEndTime(endTime);
        }
        model.addObject("events", eventService.getFilteredEventsPage(page, patientName, date, timePeriodInfo));
        model.addObject("pageCount", eventService.getCountOfPages());
        model.addObject("currentPage", page);
        model.addObject("searchDate", date);
        model.addObject("searchPatientName", patientName);
        model.addObject("searchStartTime", startTime);
        model.addObject("searchEndTime", endTime);
        return model;
    }

}
