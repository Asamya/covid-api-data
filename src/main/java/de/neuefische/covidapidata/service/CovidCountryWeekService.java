package de.neuefische.covidapidata.service;

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

    public List<ApiCovidService> getConfirmedCasesForWeek(){

    }

}
