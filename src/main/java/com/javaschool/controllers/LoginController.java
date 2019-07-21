package com.javaschool.controllers;

import com.javaschool.dao.objects.Employee;
import com.javaschool.services.interfaces.LoginService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@NoArgsConstructor
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView start() {
        return new ModelAndView("login", "employee", new Employee());
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute Employee employee) {
        ModelAndView model = new ModelAndView();
        if (loginService.checkEmployee(employee)) {
            model.setViewName("account");
            model.addObject("employee", loginService.getEmployeeByLogin(employee.getLogin()));
        } else {
            model.setViewName("login");
        }
        return model;
    }

}
