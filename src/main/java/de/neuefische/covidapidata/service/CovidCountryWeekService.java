package de.neuefische.covidapidata.service;

import de.neuefische.covidapidata.model.ApiCovidModel;
import de.neuefische.covidapidata.model.CovidModel;
import de.neuefische.covidapidata.model.CovidSchoolTodayModel;
import de.neuefische.covidapidata.service.apiService.ApiCovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidCountryWeekService {

    private final ApiCovidService apiCovidService;

    @Autowired
    public CovidCountryWeekService(ApiCovidService apiCovidService) {
        this.apiCovidService = apiCovidService;
    }

    public CovidModel getConfirmedCasesForWeek(String country){
        ApiCovidModel[] covidValues = apiCovidService.getCovidApiCountryConfirmedLastWeek(country);
        int averageCasesLastSevenDays = (covidValues[6].getCases() - covidValues[0].getCases()) / 7;
        return new CovidModel(averageCasesLastSevenDays, country);
    }

    public CovidSchoolTodayModel checkIfSchoolIsPossibleToday(String country) {
        int averageSevenDays = getConfirmedCasesForWeek(country).getCases();
        return new CovidSchoolTodayModel(averageSevenDays < 100);
    }
}
