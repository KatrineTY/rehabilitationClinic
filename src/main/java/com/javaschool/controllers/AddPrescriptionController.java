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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;
import java.util.stream.Collectors;

@Controller
@NoArgsConstructor
@Slf4j
public class AddPrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/add-prescription", method = RequestMethod.GET)
    public ModelAndView addPrescription() {
        log.info("requested add prescription page");
        return new ModelAndView("addPrescription", "prescriptionInfo", new PrescriptionInfo());
    }

    @RequestMapping(value = "/add-prescription", method = RequestMethod.POST)
    public String addPrescription(@Validated({Default.class, EditGroup.class}) @ModelAttribute PrescriptionInfo prescriptionInfo,
                                  BindingResult bindingResult,
                                  @SessionAttribute(name = "empName") String empName) {
        if (bindingResult.hasErrors()) {
            log.debug("attempted to add prescription: {} with errors in the following fields: {}",
                    prescriptionInfo.toString(),
                    bindingResult.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()));
            return "addPrescription";
        }
        prescriptionService.addPrescription(prescriptionInfo, empName);
        log.debug("successfully added prescription: {}", prescriptionInfo.toString());
        return "redirect:account";
    }

}
