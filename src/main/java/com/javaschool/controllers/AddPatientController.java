package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.PatientService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String addPatient(@Valid @ModelAttribute PatientInfo patientInfo, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "addPatient";
        }
        patientService.addPatient(patientInfo);
        return "redirect:account";
    }

}
