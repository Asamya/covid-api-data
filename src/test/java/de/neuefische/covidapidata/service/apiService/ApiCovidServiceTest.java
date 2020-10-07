package de.neuefische.covidapidata.service.apiService;

import de.neuefische.covidapidata.model.ApiCovidModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

class ApiCovidServiceTest {

    private final ApiCovidService testService = new ApiCovidService();

    @Test
    void testForCountryInUrl() {
        //Given
        String givenCountry = "Germany";

        //When
        String actualCombinedUrl = testService.combinedUrl(givenCountry);

        //Then
        assertThat(actualCombinedUrl, is("https://api.covid19api.com/total/country/germany"+
                "/status/confirmed?from=2020-09-30T00:00:00Z&to=2020-10-07T00:00:00Z"));
    }

    /*
    // Rest template mock to test method
    @Test
    void testIfTemplateLooksLikeModel() {
        //Given
        ApiCovidModel newModel = new ApiCovidModel();

        //When

        //Then

    }

     */
}