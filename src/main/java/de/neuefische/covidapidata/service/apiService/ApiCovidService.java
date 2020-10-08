package de.neuefische.covidapidata.service.apiService;

import de.neuefische.covidapidata.model.ApiCovidModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class ApiCovidService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String combinedUrl(String country) {
        LocalDate dayBefore = LocalDate.now().minusDays(1);
        LocalDate lastSevenDays = LocalDate.now().minusDays(7);
        return "https://api.covid19api.com/total/country/"+country.toLowerCase()+
                "/status/confirmed?from="+lastSevenDays+"T00:00:00Z&to="+dayBefore+"T00:00:00Z";
    }

    /*
    public String startToTodayUrl(String country) {
        LocalDate dayBefore = LocalDate.now().minusDays(1);
        return "https://api.covid19api.com/total/country/"+country+
                "/status/confirmed?from=2020-03-01T00:00:00Z&to="+dayBefore+"T00:00:00Z";
    }

     */

    public ApiCovidModel[] getCovidApiCountryConfirmedLastWeek(String country) {
        String url = combinedUrl(country);
        ResponseEntity<ApiCovidModel[]> response = restTemplate.getForEntity(url, ApiCovidModel[].class);
        response.getStatusCode();
        return response.getBody();
    }

}
