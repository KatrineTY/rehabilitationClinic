package com.javaschool.controllers;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.services.interfaces.PrescriptionService;
import com.javaschool.validation.groups.EditGroup;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;

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
    public ModelAndView editPrescription(@Validated({Default.class, EditGroup.class}) @ModelAttribute PrescriptionInfo prescriptionInfo,
                                         BindingResult bindingResult,
                                         @SessionAttribute(name = "empName") String empName) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.setViewName("editPrescription");
        } else {
            prescriptionService.updatePrescriptionInfo(prescriptionInfo, empName);
            model.setViewName("redirect:../get-prescriptions-list");
        }
        return model;
    }

    @RequestMapping(value = "/delete-prescription", method = RequestMethod.POST)
    public ModelAndView deletePrescription(@RequestParam(name = "id") int id) {
        prescriptionService.deletePrescriptionWithEvents(id);
        return new ModelAndView("redirect:get-prescriptions-list");
    }

}
