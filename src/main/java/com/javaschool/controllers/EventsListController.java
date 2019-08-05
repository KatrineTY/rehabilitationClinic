package com.javaschool.controllers;

import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@NoArgsConstructor
public class EventsListController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/take-task/{id}", method = RequestMethod.POST)
    public ModelAndView takeTask(@PathVariable int id, HttpSession httpSession) {
        Object empName = httpSession.getAttribute("empName");
        ModelAndView model = new ModelAndView();
        if (empName == null) {
            model.setViewName("error403");
        } else {
            eventService.takeTask(id, (String) empName);
            model.setViewName("eventsList");
            model.addObject("events", eventService.getEvents());
        }
        return model;
    }

    @RequestMapping(value = "/reject-task", method = RequestMethod.POST)
    public ModelAndView rejectTask(@RequestParam(name = "event.id") int eventId,
                                   @RequestParam(name = "comment") String comment,
                                   HttpSession httpSession) {
        Object empName = httpSession.getAttribute("empName");
        ModelAndView model = new ModelAndView();
        if (empName == null) {
            model.setViewName("error403");
        } else {
            eventService.rejectTask(eventId, (String) empName, comment);
            model.setViewName("eventsList");
            model.addObject("events", eventService.getEvents());
        }
        return model;
    }

}
