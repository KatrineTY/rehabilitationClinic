package com.javaschool.controllers;

import com.javaschool.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HintsController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/getDoctors", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getTags() {
        List<String> data = new ArrayList<>();
        data.add("ads");
        data.add("asdfasd");
        data.add("asdfdsf");
        data.add("asdfasd");
        data.add("asdfasdf");
        data.add("sdf");
        data.add("sdf");
        return data;
    }

}
