package com.javaschool.controllers;

import com.javaschool.dto.PatientInfo;
import com.javaschool.services.interfaces.AccountService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@NoArgsConstructor
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account")
    public String getAccount() {
        return "account";
    }
    @RequestMapping(value = "/new-patient", method = RequestMethod.GET)
    public ModelAndView newPatient() {
        return new ModelAndView("addPatient", "patientInfo", new PatientInfo());
    }

    @RequestMapping(value = "/search-patient", method = RequestMethod.GET)
    public ModelAndView searchPatient() {
        return new ModelAndView("searchPatient", "patients", accountService.getPatients());
    }

}
