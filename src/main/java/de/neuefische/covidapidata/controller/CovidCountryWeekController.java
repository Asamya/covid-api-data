package de.neuefische.covidapidata.controller;

import de.neuefische.covidapidata.model.CovidModel;
import de.neuefische.covidapidata.service.CovidCountryWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("covidweekcountry")
public class CovidCountryWeekController {

    private final CovidCountryWeekService covidCountryWeekService;

    @Autowired
    public CovidCountryWeekController(CovidCountryWeekService covidCountryWeekService) {
        this.covidCountryWeekService = covidCountryWeekService;
    }

    @GetMapping("{country}")
    public CovidModel getCasesForLastSevenDays(@PathVariable String country){
        return covidCountryWeekService.getConfirmedCasesForWeek(country);
    }



}
