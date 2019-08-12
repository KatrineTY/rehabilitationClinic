package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.PatientService;
import com.javaschool.validation.groups.AddGroup;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;

@Controller
@NoArgsConstructor
public class AddPatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/add-patient", method = RequestMethod.GET)
    public ModelAndView addPatient() {
        return new ModelAndView("addPatient", "patientInfo", new PatientInfo());
    }

    @RequestMapping(value = "/add-patient", method = RequestMethod.POST)
    public String addPatient(@Validated({Default.class, AddGroup.class}) @ModelAttribute PatientInfo patientInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPatient";
        }
        patientService.addPatient(patientInfo);
        return "redirect:account";
    }

}
