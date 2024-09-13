package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dtos.CityInfoDTO;
import org.example.dtos.CurrentDataDTO;
import org.example.dtos.MunicipalityDTO;
import org.example.dtos.WeatherInfoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {
    private static WeatherService weatherService;
    private WeatherInfoDTO w1;
    private CurrentDataDTO d1;


    @BeforeAll
    static void beforeAll() {
        weatherService = new WeatherService(new ObjectMapper());
    }

    @BeforeEach
    void setUp() {
        d1 = new CurrentDataDTO(30.0, "Rather cloudy", 90, "5.6 m/s");
        w1 = new WeatherInfoDTO("Roskilde", d1); // my expected.
    }

    @Test
    void getWeather() {
        WeatherInfoDTO expected = w1;
        WeatherInfoDTO actual = weatherService.getWeather(w1.getLocationName());
        assertEquals(expected, actual);
    }
}