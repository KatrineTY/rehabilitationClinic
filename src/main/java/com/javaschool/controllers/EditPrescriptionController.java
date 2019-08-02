package com.javaschool.controllers;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.services.interfaces.PrescriptionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@NoArgsConstructor
public class EditPrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/edit-prescription/{id}", method = RequestMethod.GET)
    public ModelAndView getPrescription(@PathVariable String id) {
        return new ModelAndView("editPrescription",
                "prescriptionInfo", prescriptionService.getPrescriptionById(Integer.parseInt(id)));
    }

    @RequestMapping(value = "/edit-prescription", method = RequestMethod.POST)
    public ModelAndView editPrescription(@ModelAttribute PrescriptionInfo prescriptionInfo, HttpSession httpSession) {
        Object empName = httpSession.getAttribute("empName");
        ModelAndView model = new ModelAndView();
        if (empName == null) {
            model.setViewName("error403");
        } else {
            prescriptionService.updatePrescriptionInfo(prescriptionInfo, (String) empName);
            model.setViewName("prescriptionsList");
            model.addObject("prescriptions", prescriptionService.getAllPrescriptions());
        }
        return model;
    }

    @RequestMapping(value = "/delete-prescription/{id}", method = RequestMethod.GET)
    public ModelAndView deletePrescription(@PathVariable String id) {
        prescriptionService.deletePrescriptionById(Integer.parseInt(id));
        return new ModelAndView("prescriptionsList", "prescriptions", prescriptionService.getAllPrescriptions());
    }

}
