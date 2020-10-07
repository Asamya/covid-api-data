package de.neuefische.covidapidata.service.apiService;

import de.neuefische.covidapidata.model.ApiCovidModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiCovidServiceBootTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    ApiCovidService apiCovidService;

    /*@Test
    void testIfTemplateLooksLikeModel() {
        //Given
        ApiCovidModel newModel = new ApiCovidModel();
        ApiCovidModel[] responseArray = restTemplate.getForEntity();

        //When
        when(apiCovidService.getCovidApiCountryConfirmedLastWeek("germany")).thenReturn(ApiCovidService);

        //Then
    }

     */


}