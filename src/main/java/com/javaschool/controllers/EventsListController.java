package com.javaschool.controllers;

import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
public class EventsListController {
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/take-task", method = RequestMethod.POST)
    public ModelAndView takeTask(@RequestParam(name = "event.id") int id,
                                 @SessionAttribute(name = "empName") String empName) {
        ModelAndView model = new ModelAndView();
        eventService.takeTask(id, empName);
        model.setViewName("redirect:get-events-list");
        return model;
    }

    @RequestMapping(value = "/reject-task", method = RequestMethod.POST)
    public ModelAndView rejectTask(@RequestParam(name = "event.id") int eventId,
                                   @RequestParam(name = "comment") String comment,
                                   @SessionAttribute(name = "empName") String empName) {
        ModelAndView model = new ModelAndView();
        eventService.rejectTask(eventId, empName, comment);
        model.setViewName("redirect:get-events-list");
        return model;
    }

}
