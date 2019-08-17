package com.javaschool.controllers;

import com.javaschool.dto.PrescriptionInfo;
import com.javaschool.services.interfaces.PrescriptionService;
import com.javaschool.validation.groups.EditGroup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;
import java.util.stream.Collectors;

@Controller
@NoArgsConstructor
@Slf4j
public class EditPrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/edit-prescription/{id}", method = RequestMethod.GET)
    public ModelAndView getPrescription(@PathVariable int id) {
        log.info("requested to edit prescription with id: {}", id);
        return new ModelAndView("editPrescription",
                "prescriptionInfo", prescriptionService.getPrescription(id));
    }

    @RequestMapping(value = "/edit-prescription/{id}", method = RequestMethod.POST)
    public ModelAndView editPrescription(@Validated({Default.class, EditGroup.class}) @ModelAttribute PrescriptionInfo prescriptionInfo,
                                         BindingResult bindingResult,
                                         @SessionAttribute(name = "empName") String empName) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            log.debug("attempted to edit prescription info with id: {} with errors in the following fields: {}",
                    prescriptionInfo.getPrescription().getId(),
                    bindingResult.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()));
            model.setViewName("editPrescription");
        } else {
            prescriptionService.updatePrescriptionInfo(prescriptionInfo, empName);
            log.debug("successfully edited prescription: {}", prescriptionInfo.toString());
            model.setViewName("redirect:../get-prescriptions-list");
        }
        return model;
    }

    @RequestMapping(value = "/delete-prescription", method = RequestMethod.POST)
    public ModelAndView deletePrescription(@RequestParam(name = "id") int id, @SessionAttribute(name = "empName") String empName) {
        log.info("attempted to delete prescription with id: {}", id);
        prescriptionService.deletePrescriptionWithEvents(id);
        log.info("successfully deleted prescription with id: {}", id);
        return new ModelAndView("redirect:get-prescriptions-list");
    }

}
