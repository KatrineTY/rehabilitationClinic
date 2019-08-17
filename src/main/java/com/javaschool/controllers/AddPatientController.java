package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.PatientService;
import com.javaschool.validation.groups.AddGroup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;
import java.util.stream.Collectors;

@Controller
@NoArgsConstructor
@Slf4j
public class AddPatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/add-patient", method = RequestMethod.GET)
    public ModelAndView addPatient() {
        log.info("requested add patient page");
        return new ModelAndView("addPatient", "patientInfo", new PatientInfo());
    }

    @RequestMapping(value = "/add-patient", method = RequestMethod.POST)
    public String addPatient(@Validated({Default.class, AddGroup.class}) @ModelAttribute PatientInfo patientInfo,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("attempted to add patient: {} with errors in the following fields: {}",
                    patientInfo.toString(),
                    bindingResult.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()));
            return "addPatient";
        }
        patientService.addPatient(patientInfo);
        log.debug("successfully added patient: {}", patientInfo.toString());
        return "redirect:account";
    }

}
