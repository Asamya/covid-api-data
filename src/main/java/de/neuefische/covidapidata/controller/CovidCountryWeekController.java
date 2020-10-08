package de.neuefische.covidapidata.controller;

import de.neuefische.covidapidata.model.CovidModel;
import de.neuefische.covidapidata.model.CovidSchoolTodayModel;
import de.neuefische.covidapidata.service.CovidCountryWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("school/{country}")
    public CovidSchoolTodayModel checkIfSchoolIsPossibleToday(@PathVariable String country) {
        return covidCountryWeekService.checkIfSchoolIsPossibleToday(country);
    }

    @GetMapping("cases/over1000/{country}")
    public List<CovidModel> getDaysWithOver1000Cases(@PathVariable String country) {
        return covidCountryWeekService.getDaysWithOver1000Cases(covidCountryWeekService.listOfCasesPerCountry(country));
    }

    @GetMapping("cases/any/{country}")
    public Optional<CovidModel> getAnyCasesOver50000(@PathVariable String country) {
        return covidCountryWeekService.getAnyCasesOver50000(covidCountryWeekService.listOfCasesPerCountry(country));
    }

}
