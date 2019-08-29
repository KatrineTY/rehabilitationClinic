package com.javaschool.controllers;

import com.javaschool.entities.Employee;
import com.javaschool.services.interfaces.AdminService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@Controller
@NoArgsConstructor
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/add-employee", method = RequestMethod.GET)
    public ModelAndView addPrescription() {
        log.info("requested add employee page");
        ModelAndView model = new ModelAndView("addEmployee");
        model.addObject("roles", adminService.getRoles());
        model.addObject("workingTimes", adminService.getWorkingTimes());
        model.addObject("employee", new Employee());
        return model;
    }

    @RequestMapping(value = "/add-employee", method = RequestMethod.POST)
    public String addPrescription(@Valid @ModelAttribute Employee employee,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.debug("attempted to add employee: {} with errors in the following fields: {}",
                    employee.toString(),
                    bindingResult.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()));
            model.addAttribute("roles", adminService.getRoles());
            model.addAttribute("workingTimes", adminService.getWorkingTimes());
            return "addEmployee";
        }
        adminService.addEmployee(employee);
        log.debug("successfully added employee: {}", employee.toString());
        return "redirect:account";
    }

    @RequestMapping(value = "/get-employees-list", method = RequestMethod.GET)
    public ModelAndView getEmployeesList() {
        return new ModelAndView("employeesList", "employees", adminService.getEmployees());
    }

    @RequestMapping(value = "/edit-employee/{id}", method = RequestMethod.GET)
    public ModelAndView getEmployee(@PathVariable int id) {
        log.info("requested to edit employee with id: {}", id);
        ModelAndView model = new ModelAndView("editEmployee");
        model.addObject("roles", adminService.getRoles());
        model.addObject("workingTimes", adminService.getWorkingTimes());
        model.addObject("employee", adminService.getEmployee(id));
        return model;
    }

    @RequestMapping(value = "/edit-employee/{id}", method = RequestMethod.POST)
    public ModelAndView editEmployee(@Valid @ModelAttribute Employee employee,
                                     BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            log.debug("attempted to edit employee info with id: {} with errors in the following fields: {}",
                    employee.getId(),
                    bindingResult.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList()));
            model.addObject("roles", adminService.getRoles());
            model.addObject("workingTimes", adminService.getWorkingTimes());
            model.setViewName("editEmployee");
            return model;
        }
        adminService.updateEmployee(employee);
        log.debug("successfully edited employee: {}", employee.toString());
        model.setViewName("redirect:../get-employees-list");
        return model;
    }

    @RequestMapping(value = "/disable-employee", method = RequestMethod.POST)
    public ModelAndView disableEmployee(@RequestParam(name = "id") int id) {
        log.info("attempted to disable employee with id: {}", id);
        adminService.disableEmployee(id);
        log.info("successfully disabled employee with id: {}", id);
        return new ModelAndView("redirect:get-employees-list");
    }

}
