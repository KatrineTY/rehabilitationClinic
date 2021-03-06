package com.javaschool.controllers;

import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
@Slf4j
public class ScheduleController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/patient-schedule/{id}", method = RequestMethod.GET)
    public ModelAndView getPatientSchedule(@PathVariable int id) {
        log.info("requested schedule of the patient with id: {}", id);
        return new ModelAndView("patientSchedule", "events", eventService.getEvents(id));
    }

}
