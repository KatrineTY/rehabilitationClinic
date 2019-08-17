package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.PatientManipulationService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@NoArgsConstructor
@Slf4j
public class EditPatientController {
    @Autowired
    private PatientManipulationService patientManipulationService;

    @RequestMapping(value = "/edit-patient/{id}", method = RequestMethod.GET)
    public ModelAndView getPatient(@PathVariable int id) {
        log.info("requested to edit patient with id: {}", id);
        ModelAndView model = new ModelAndView("editPatient");
        model.addObject("patientInfo", patientManipulationService.getPatientInfo(id));
        model.addObject("doctors", patientManipulationService.getAttendingDoctorNames());
        return model;
    }

    @RequestMapping(value = "/edit-patient/{id}", method = RequestMethod.POST)
    public ModelAndView editPatient(@Valid @ModelAttribute PatientInfo patientInfo,
                                    BindingResult bindingResult,
                                    @SessionAttribute(name = "empName") String empName) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            log.debug("attempted to edit patient info with id: {} with errors in the following fields: {}",
                    patientInfo.getPatient().getId(),
                    bindingResult.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()));
            model.addObject("doctors", patientManipulationService.getAttendingDoctorNames());
            model.setViewName("editPatient");
            return model;
        }
        patientManipulationService.updatePatientInfo(patientInfo);
        log.debug("successfully edited patient: {}", patientInfo.toString());
        model.setViewName("redirect:../patients-list");
        return model;
    }

    @RequestMapping(value = "/discharge-patient", method = RequestMethod.POST)
    public ModelAndView dischargePatient(@RequestParam(name = "id") int id) {
        log.info("attempted to discharge patient with id: {}", id);
        patientManipulationService.dischargePatient(id);
        log.info("successfully discharged patient with id: {}", id);
        return new ModelAndView("redirect:patients-list");

    }

}