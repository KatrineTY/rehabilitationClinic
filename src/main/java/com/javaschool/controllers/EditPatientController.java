package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.PatientManipulationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
public class EditPatientController {
    @Autowired
    private PatientManipulationService patientManipulationService;

    @RequestMapping(value = "/edit-patient/{id}", method = RequestMethod.GET)
    public ModelAndView getPatient(@PathVariable int id) {
        return new ModelAndView("editPatient", "patientInfo", patientManipulationService.getPatientInfo(id));
    }

    @RequestMapping(value = "/edit-patient", method = RequestMethod.POST)
    public ModelAndView editPatient(@ModelAttribute PatientInfo patientInfo) {
        patientManipulationService.updatePatientInfo(patientInfo);
        return new ModelAndView("searchPatient", "patientCards", patientManipulationService.getPatientCards());

    }

    @RequestMapping(value = "/discharge-patient", method = RequestMethod.POST)
    public ModelAndView dischargePatient(@RequestParam(name = "id") int id) {
        patientManipulationService.dischargePatient(id);
        return new ModelAndView("searchPatient", "patientCards", patientManipulationService.getPatientCards());

    }

}