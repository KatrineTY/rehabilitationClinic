package com.javaschool.controllers;

import com.javaschool.entities.Patient;
import com.javaschool.services.interfaces.PatientService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@NoArgsConstructor
public class AddPatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/add-patient", method = RequestMethod.POST)
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "account";
    }

}
