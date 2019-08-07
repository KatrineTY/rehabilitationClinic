package com.javaschool.controllers;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.services.interfaces.PrescriptionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@NoArgsConstructor
public class EditPrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/edit-prescription/{id}", method = RequestMethod.GET)
    public ModelAndView getPrescription(@PathVariable int id) {
        return new ModelAndView("editPrescription",
                "prescriptionInfo", prescriptionService.getPrescription(id));
    }

    @RequestMapping(value = "/edit-prescription/{id}", method = RequestMethod.POST)
    public ModelAndView editPrescription(@Valid @ModelAttribute PrescriptionInfo prescriptionInfo, BindingResult bindingResult, HttpSession httpSession) {
        String empName = (String) httpSession.getAttribute("empName");
        ModelAndView model = new ModelAndView();

        if (prescriptionInfo.getPrescription().getType().getKind().equals("Medicament")
                && !prescriptionInfo.getPrescription().getDose().matches("\\d+mg|\\d+ml")) {
            bindingResult.rejectValue("prescription.dose", "prescription.dose", "Wrong dose format");
            model.setViewName("editPrescription");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("editPrescription");
        } else {
            prescriptionService.updatePrescriptionInfo(prescriptionInfo, empName);
            model.setViewName("redirect:get-prescriptions-list");
        }
        return model;
    }

    @RequestMapping(value = "/delete-prescription", method = RequestMethod.POST)
    public ModelAndView deletePrescription(@RequestParam(name = "id") int id) {
        prescriptionService.deletePrescription(id);
        return new ModelAndView("redirect:get-prescriptions-list");
    }

}
