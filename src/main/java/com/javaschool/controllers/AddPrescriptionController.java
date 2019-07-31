package com.javaschool.controllers;


import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.entities.Prescription;
import com.javaschool.services.interfaces.PrescriptionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
public class AddPrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/add-prescription", method = RequestMethod.GET)
    public ModelAndView addPrescription() {
        return new ModelAndView("addPrescription", "prescription", new Prescription());
    }

    @RequestMapping(value = "/add-prescription", method = RequestMethod.POST)
    public String addPrescription(@ModelAttribute PrescriptionInfo prescriptionInfo) {
        prescriptionService.addPrescription(prescriptionInfo);
        return "account";
    }

}
