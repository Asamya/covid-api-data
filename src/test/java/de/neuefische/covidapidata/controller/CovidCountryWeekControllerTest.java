package de.neuefische.covidapidata.controller;

import de.neuefische.covidapidata.model.CovidModel;
import de.neuefische.covidapidata.model.CovidSchoolTodayModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CovidCountryWeekControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void checkForResultOfCasesForLastSevenDays() {
        //Given
        String url = "http://localhost:"+port+"/covidweekcountry/germany";

        //When
        ResponseEntity<CovidModel> response = restTemplate.getForEntity(url, CovidModel.class);


        //Then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new CovidModel(2030, "germany")));

    }

    @Test
    void checkIfSchoolIsPossibleToday() {
        //Given
        String url = "http://localhost:"+port+"/covidweekcountry/school/germany";

        //When
        ResponseEntity<CovidSchoolTodayModel> response = restTemplate.getForEntity(url, CovidSchoolTodayModel.class);

        //Then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new CovidSchoolTodayModel(false)));
    }
}