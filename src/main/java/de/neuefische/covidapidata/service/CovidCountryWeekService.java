package de.neuefische.covidapidata.service;

import de.neuefische.covidapidata.model.ApiCovidModel;
import de.neuefische.covidapidata.model.CovidModel;
import de.neuefische.covidapidata.model.CovidSchoolTodayModel;
import de.neuefische.covidapidata.service.apiService.ApiCovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CovidCountryWeekService {

    private final ApiCovidService apiCovidService;

    @Autowired
    public CovidCountryWeekService(ApiCovidService apiCovidService) {
        this.apiCovidService = apiCovidService;
    }

    public CovidModel getConfirmedCasesForWeek(String country){
        ApiCovidModel[] covidValues = apiCovidService.getCovidApiCountryConfirmedLastWeek(country);
        double averageCasesLastSevenDays = (covidValues[covidValues.length - 1].getCases() - covidValues[0].getCases()) / 7;
        return new CovidModel((int) averageCasesLastSevenDays, country, "da kommt ein Datum rein.");
    }

    public CovidSchoolTodayModel checkIfSchoolIsPossibleToday(String country) {
        int averageSevenDays = getConfirmedCasesForWeek(country).getCases();
        return new CovidSchoolTodayModel(averageSevenDays < 100);
    }

    public List<CovidModel> getWeekendCases(List<ApiCovidModel> apiCovidModels) {
        return apiCovidModels.stream()
                .filter(apiCovidModel -> isWeekend(apiCovidModel))
                .map(apiCovidModel -> new CovidModel(apiCovidModel.getCases(),apiCovidModel.getDate(), apiCovidModel.getCountry()))
                .collect(Collectors.toList());
    }

    private boolean isWeekend(ApiCovidModel covidItem){
        DayOfWeek dayOfWeek = LocalDate.parse(covidItem.getDate(), DateTimeFormatter.ISO_ZONED_DATE_TIME).getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }

    public List<ApiCovidModel> listOfCasesPerCountry(String country){
        ApiCovidModel[] covidValues = apiCovidService.getCovidApiCountryConfirmedLastWeek(country);
        return Arrays.stream(covidValues)
                .map(covidItem -> new ApiCovidModel(covidItem.getCases(), covidItem.getDate(), covidItem.getCountry()))
                .collect(Collectors.toList());
    }

    public List<CovidModel> getDaysWithOver1000Cases(List<ApiCovidModel> apiCovidModels){
        return apiCovidModels.stream()
                .filter(apiCovidModel -> apiCovidModel.getCases() > 1000)
                .map(apiCovidModel -> new CovidModel(apiCovidModel.getCases(),apiCovidModel.getDate(), apiCovidModel.getCountry()))
                .collect(Collectors.toList());

    }

    public Optional<CovidModel> getAnyCasesOver50000(List<ApiCovidModel> apiCovidModels){
        return apiCovidModels.stream()
                .filter(apiCovidModel -> apiCovidModel.getCases() > 50000)
                .map(apiCovidModel -> new CovidModel(apiCovidModel.getCases(), apiCovidModel.getDate(), apiCovidModel.getCountry()))
                .findAny();
    }







    /*public List<ApiCovidModel> getConfirmedCases(List<CovidModel> values) {
        return values.stream()
                .map(value -> new CovidModel(value.getCountry(), (int) value.getCases()))
                .collect(Collectors.toList());
    }


    public ApiCovidModel getCasesForWeekends(ApiCovidModel apiCovidModel) {
        return Arrays.stream(apiCovidService.getCovidApiListOfConfirmedCases(country))
                .filter(weekend ->)
    }

    private boolean isWeekend(ApiCovidModel apiCovidModel){
        DayOfWeek dayOfWeek = LocalDate.parse(apiCovidModel.getDate()).getDayOfWeek());
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }

     */
}
