package de.neuefische.covidapidata.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCovidModel {

    @JsonProperty("Cases")
    private int cases;

    @JsonProperty("Date")
    private String date;


    @JsonProperty("Country")
    private String country;

}
