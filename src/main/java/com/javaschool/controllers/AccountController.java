package com.javaschool.controllers;

import com.javaschool.services.interfaces.AccountService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@NoArgsConstructor
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account")
    public String getAccount(HttpSession httpSession) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String employeeName = accountService.getEmployeeNameByLogin(principal.getUsername());
        httpSession.setAttribute("empName", employeeName);
        return "account";
    }

    @RequestMapping(value = "/patients-list", method = RequestMethod.GET)
    public ModelAndView searchPatient() {
        return new ModelAndView("patientsList", "patientCards", accountService.getPatientCards());
    }

    @RequestMapping(value = "/get-prescriptions-list", method = RequestMethod.GET)
    public ModelAndView getPrescriptionsList() {
        return new ModelAndView("prescriptionsList", "prescriptions", accountService.getPrescriptions());
    }

}
