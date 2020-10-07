package de.neuefische.covidapidata.service.apiService;

import de.neuefische.covidapidata.model.ApiCovidModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCovidService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String combinedUrl(String country) {
        return "https://api.covid19api.com/total/country/"+country.toLowerCase()+
                "/status/confirmed?from=2020-09-30T00:00:00Z&to=2020-10-07T00:00:00Z";
    }

    public ApiCovidModel[] getCovidApiCountryConfirmedLastWeek(String country) {
        ResponseEntity<ApiCovidModel[]> response = restTemplate.getForEntity(combinedUrl(country), ApiCovidModel[].class);
        response.getStatusCode();
        return response.getBody();
    }

}
