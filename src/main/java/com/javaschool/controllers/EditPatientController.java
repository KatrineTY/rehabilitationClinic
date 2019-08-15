package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.PatientManipulationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@NoArgsConstructor
public class EditPatientController {
    @Autowired
    private PatientManipulationService patientManipulationService;

    @RequestMapping(value = "/edit-patient/{id}", method = RequestMethod.GET)
    public ModelAndView getPatient(@PathVariable int id) {
        ModelAndView model = new ModelAndView("editPatient");
        model.addObject("patientInfo", patientManipulationService.getPatientInfo(id));
        model.addObject("doctors", patientManipulationService.getAttendingDoctorNames());
        return model;
    }

    @RequestMapping(value = "/edit-patient/{id}", method = RequestMethod.POST)
    public ModelAndView editPatient(@Valid @ModelAttribute PatientInfo patientInfo, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.addObject("doctors", patientManipulationService.getAttendingDoctorNames());
            model.setViewName("editPatient");
            return model;
        }
        patientManipulationService.updatePatientInfo(patientInfo);
        model.setViewName("redirect:../patients-list");
        return model;
    }

    @RequestMapping(value = "/discharge-patient", method = RequestMethod.POST)
    public ModelAndView dischargePatient(@RequestParam(name = "id") int id) {
        patientManipulationService.dischargePatient(id);
        return new ModelAndView("redirect:patients-list");

    }

}