package com.javaschool.activemq;

import com.javaschool.dto.EventDto;
import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
public class MessagesController {
    @Autowired
    private EventService eventService;

    @RequestMapping(name = "/events-for-tabloid", method = RequestMethod.GET)
    public List<EventDto> getCurrentEvents() {
        return eventService.getEventDtosPerDay();
    }

}
