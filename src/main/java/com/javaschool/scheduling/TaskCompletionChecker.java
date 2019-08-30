package com.javaschool.scheduling;

import com.javaschool.entities.Event;
import com.javaschool.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

@Component
public class TaskCompletionChecker {
    private static final int MEDICAMENT_TIME = 5;
    private static final int PROCEDURE_TIME = 30;
    @Autowired
    private EventService eventService;
    @Autowired
    public JavaMailSender emailSender;

    @Scheduled(fixedDelay = 300_000, initialDelay = 5000)
    public void checkMedicineTasksCompletion() {
        List<Event> nearestMedicinesEvents = eventService.getNearestMedicinesEvents();
        for (Event event : nearestMedicinesEvents) {
            if (event.getEndTaskTime() == null || MINUTES.between(event.getStartTaskTime(), event.getEndTaskTime()) > MEDICAMENT_TIME) {
                sendMessage(event, MEDICAMENT_TIME);
            }
        }
    }

    @Scheduled(fixedDelay = 1800_000, initialDelay = 5000)
    public void checkProceduresTasksCompletion() {
        List<Event> nearestProceduresEvents = eventService.getNearestProceduresEvents();
        for (Event event : nearestProceduresEvents) {
            if (event.getEndTaskTime() == null || MINUTES.between(event.getStartTaskTime(), event.getEndTaskTime()) > PROCEDURE_TIME) {
                sendMessage(event, PROCEDURE_TIME);
            }
        }
    }

    private void sendMessage(Event event, int time) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("katrine192002@gmail.com");
        message.setFrom("katrine192002@gmail.com");
        message.setSubject("Task delay");
        message.setText(String.format("Nurse: %s didn't finish a task (id: %d) in fixed time (%d min)",
                event.getNurse().getName(), event.getId(), time));
        emailSender.send(message);
    }

}
