package com.javaschool.controllers;

import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.AutocompleteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@NoArgsConstructor
public class AutocompleteController {
    @Autowired
    private AutocompleteService autocompleteService;

    @RequestMapping(value = "/getPatients", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getPatients() {
        return autocompleteService.getPatientNames();
    }

    @RequestMapping(value = "/getProceduresAndMedicines", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<String>> getProceduresAndMedicines() {
        return autocompleteService.getProcedureAndMedicamentNames()
                .stream()
                .collect(Collectors.groupingBy(ProcedureAndMedicament::getKind,
                        Collectors.mapping(ProcedureAndMedicament::getName, Collectors.toList())));
    }
    
}
