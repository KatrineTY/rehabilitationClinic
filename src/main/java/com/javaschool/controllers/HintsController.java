package com.javaschool.controllers;

import com.javaschool.entities.ProcedureAndMedicament;
import com.javaschool.services.interfaces.HintsService;
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
public class HintsController {
    @Autowired
    private HintsService hintsService;

    @RequestMapping(value = "/getPatients", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getPatients() {
        return hintsService.getPatientNames();
    }

    @RequestMapping(value = "/getProceduresAndMedicines", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<String>> getProceduresAndMedicines() {
        return hintsService.getProcedureNames()
                .stream()
                .collect(Collectors.groupingBy(ProcedureAndMedicament::getKind,
                        Collectors.mapping(ProcedureAndMedicament::getName, Collectors.toList())));
    }

    @RequestMapping(value = "/getMedicines", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getMedicines() {
        return hintsService.getMedicamentNames();
    }


    private class Pair {
        String kind;
        String name;

        public Pair(String kind, String name) {
            this.kind = kind;
            this.name = name;
        }

        public String getKind() {
            return kind;
        }

        public String getName() {
            return name;
        }

    }

}
