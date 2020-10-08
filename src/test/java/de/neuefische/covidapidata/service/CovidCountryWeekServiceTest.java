package de.neuefische.covidapidata.service;

import de.neuefische.covidapidata.model.ApiCovidModel;
import de.neuefische.covidapidata.model.CovidModel;
import de.neuefische.covidapidata.service.apiService.ApiCovidService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CovidCountryWeekServiceTest {

    private ApiCovidService apiCovidService = new ApiCovidService();
    private CovidCountryWeekService covidCountryWeekService = new CovidCountryWeekService(apiCovidService);

    @Test
    void testIfGetWeekendCasesGivesBackAList() {
        //Given
        List<ApiCovidModel> apiCovidModel = List.of(
                new ApiCovidModel(0,"2020-09-05T00:00:00Z", "Germany"),
                new ApiCovidModel(0,"2020-09-27T00:00:00Z", "Germany"),
                new ApiCovidModel(0,"2020-09-08T00:00:00Z", "Germany")
        );

        //When
        List<CovidModel> actual = covidCountryWeekService.getWeekendCases(apiCovidModel);

        //Then
        assertThat(actual, contains(
                new CovidModel(0,"2020-09-05T00:00:00Z", "Germany"),
                new CovidModel(0,"2020-09-27T00:00:00Z", "Germany")
        ));

    }
}