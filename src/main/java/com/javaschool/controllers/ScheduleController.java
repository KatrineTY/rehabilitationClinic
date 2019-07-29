package com.javaschool.controllers;

import com.javaschool.services.interfaces.ScheduleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/patient-schedule/{id}", method = RequestMethod.GET)
    public ModelAndView getPatientSchedule(@PathVariable String id) {
        return new ModelAndView("patientSchedule", "events",
                scheduleService.getScheduleByPatientId(Integer.parseInt(id)));
    }

}
