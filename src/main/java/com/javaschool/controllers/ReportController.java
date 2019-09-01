package com.javaschool.controllers;

import com.javaschool.reports.CommonReport;
import com.javaschool.services.interfaces.EventService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@NoArgsConstructor
@Controller
@Slf4j
public class ReportController {
    @Autowired
    private EventService eventService;
    @Autowired
    private CommonReport commonReport;

    @RequestMapping(value = "/generate-report", method = RequestMethod.GET)
    public String getReportPage() {
        log.info("requested generate report");
        return "commonReport";
    }

    @RequestMapping(value = "/generate-report", method = RequestMethod.POST)
    public ModelAndView generateReport(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       @ModelAttribute(name = "searchStartDate") LocalDate startDate,
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       @ModelAttribute(name = "searchEndDate") LocalDate endDate,
                                       @ModelAttribute(name = "searchPatientName") String patientName) {
        log.info("generating report with the following params name: {} start date: {} end date {}", patientName, startDate, endDate);
        return new ModelAndView("commonReport", "events", eventService.getEvents(patientName, startDate, endDate));
    }

    @RequestMapping(value = "/generate-report/pdf", method = RequestMethod.POST)
    public void generatePdfReport(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  @ModelAttribute(name = "searchStartDate") LocalDate startDate,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  @ModelAttribute(name = "searchEndDate") LocalDate endDate,
                                  @ModelAttribute(name = "searchPatientName") String patientName,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        log.info("generating pdf report with the following params name: {} start date: {} end date {}", patientName, startDate, endDate);
        commonReport.buildPdfDocument(patientName, startDate, endDate,
                eventService.getEvents(patientName, startDate, endDate), request, response);
    }

}
