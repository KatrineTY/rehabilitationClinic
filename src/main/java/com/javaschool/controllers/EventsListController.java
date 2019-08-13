package com.javaschool.controllers;

import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@NoArgsConstructor
public class EventsListController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "get-events-list/take-task", method = RequestMethod.POST)
    public ModelAndView takeTask(@RequestParam(name = "event.id") int id,
                                 @SessionAttribute(name = "empName") String empName) {
        ModelAndView model = new ModelAndView();
        eventService.takeTask(id, empName);
        model.setViewName("redirect:../get-events-list/1");
        return model;
    }

    @RequestMapping(value = "get-events-list/reject-task", method = RequestMethod.POST)
    public ModelAndView rejectTask(@RequestParam(name = "event.id") int eventId,
                                   @RequestParam(name = "comment") String comment,
                                   @SessionAttribute(name = "empName") String empName) {
        ModelAndView model = new ModelAndView();
        eventService.rejectTask(eventId, empName, comment);
        model.setViewName("redirect:../get-events-list/1");
        return model;
    }

    @RequestMapping(value = "get-events-list/{page}")
    public ModelAndView getEventList(@PathVariable int page,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                     @RequestParam(name = "searchDate", required = false)
                                             LocalDate date,
                                     @RequestParam(name = "searchPatientName", required = false) String patientName) {
        ModelAndView model = new ModelAndView("eventsList");
        model.addObject("events", eventService.getFilteredEventsPage(page, patientName, date));
        model.addObject("pageCount", eventService.getCountOfPages());
        model.addObject("currentPage", page);
        if (date != null) {
            model.addObject("searchDate", date);
        }
        if (patientName != null) {
            model.addObject("searchPatientName", patientName);
        }
        return model;
    }

}
