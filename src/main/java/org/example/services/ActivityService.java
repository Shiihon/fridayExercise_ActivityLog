package org.example.services;

import org.example.dtos.ActivityDTO;
import org.example.dtos.CityInfoDTO;
import org.example.dtos.WeatherInfoDTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityService {
    private WeatherService weatherService;
    private CityService cityService;

    public ActivityService(WeatherService weatherService, CityService cityService) {
        this.weatherService = weatherService;
        this.cityService = cityService;
    }

    public ActivityDTO createActivity(LocalDate excersizeDate, String excersizeType, LocalTime timeOfDay, Duration duration, Double distance, String comment, String cityName) {
        WeatherInfoDTO weatherResult = weatherService.getWeather(cityName);
        CityInfoDTO cityResult = cityService.getCityInfoFromApi(cityName);

        ActivityDTO activityDTO = ActivityDTO.builder()
                .excersizeDate(excersizeDate)
                .excersizeType(excersizeType)
                .timeOfDay(timeOfDay)
                .duration(duration)
                .distance(distance)
                .comment(comment)
                .weatherInfo(weatherResult)
                .cityInfoDTO(cityResult)
                .build();

        return activityDTO;
    }
}
