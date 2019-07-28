package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.PatientService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
public class SearchPatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/edit-patient/{id}", method = RequestMethod.GET)
    public ModelAndView getPatient(@PathVariable String id) {
        return new ModelAndView("editPatient", "patientInfo", patientService.getPatientInfoByPatientId(Integer.parseInt(id)));
    }

    @RequestMapping(value = "/edit-patient", method = RequestMethod.POST)
    public ModelAndView editPatient(@ModelAttribute PatientInfo patientInfo) {
        patientService.updatePatientInfo(patientInfo);
        return new ModelAndView("searchPatient", "patientCards", patientService.getPatientCards());

    }

}
