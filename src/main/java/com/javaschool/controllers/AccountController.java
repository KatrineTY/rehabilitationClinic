package com.javaschool.controllers;

import com.javaschool.dao.objects.Patient;
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

    @RequestMapping(value = "/addPatient", method = RequestMethod.GET)
    public ModelAndView addPatient() {
        return new ModelAndView("addPatient", "patient", new Patient());
    }

    @RequestMapping(value = "/searchPatient", method = RequestMethod.GET)
    public ModelAndView searchPatient() {
        return new ModelAndView("searchPatient", "patients", accountService.getPatients());
    }

}
