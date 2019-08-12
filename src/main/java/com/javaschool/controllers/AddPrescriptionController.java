package com.javaschool.controllers;


import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.services.interfaces.PrescriptionService;
import com.javaschool.validation.groups.EditGroup;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;

@Controller
@NoArgsConstructor
public class AddPrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/add-prescription", method = RequestMethod.GET)
    public ModelAndView addPrescription() {
        return new ModelAndView("addPrescription", "prescriptionInfo", new PrescriptionInfo());
    }

    @RequestMapping(value = "/add-prescription", method = RequestMethod.POST)
    public String addPrescription(@Validated({Default.class, EditGroup.class}) @ModelAttribute PrescriptionInfo prescriptionInfo,
                                  BindingResult bindingResult,
                                  @SessionAttribute(name = "empName") String empName) {
        if (bindingResult.hasErrors()) {
            return "addPrescription";
        }
        prescriptionService.addPrescription(prescriptionInfo, empName);
        return "redirect:account";
    }

}
