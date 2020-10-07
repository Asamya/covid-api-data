package de.neuefische.covidapidata.controller;

import de.neuefische.covidapidata.service.CovidCountryWeekService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("covidweekcountry")
public class CovidCountryWeekController {

    public CovidCountryWeekController(CovidCountryWeekService covidCountryWeekService) {
        this.covidCountryWeekService = covidCountryWeekService;
    }

    private final CovidCountryWeekService covidCountryWeekService;
}
