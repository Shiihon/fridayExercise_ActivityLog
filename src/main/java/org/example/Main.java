package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.dtos.ActivityDTO;
import org.example.dtos.WeatherInfoDTO;
import org.example.services.ActivityService;
import org.example.services.CityService;
import org.example.services.WeatherService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    ObjectMapper objectMapper = new ObjectMapper();

    public Main (ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public static void main(String[] args) {
       WeatherService weatherService = new WeatherService(new ObjectMapper());
//        System.out.println(weatherService.getWeather("Rødovre"));

        CityService cityService = new CityService(new ObjectMapper());
//        System.out.println(cityService.getCityInfoFromApi("Roskilde"));

        ActivityService activityService = new ActivityService(weatherService, cityService);
       ActivityDTO createdActivity = activityService.createActivity(LocalDate.now(), "Run", LocalTime.now(), Duration.ofMinutes(30), 5.0, "No comment", "Roskilde");
       System.out.println(createdActivity);
    }
}