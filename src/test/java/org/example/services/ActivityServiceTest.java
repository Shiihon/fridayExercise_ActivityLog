package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dtos.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityServiceTest {
    private static ActivityService activityService;
    private ActivityDTO a1;
    private WeatherInfoDTO w1;
    private CityInfoDTO c1;
    private CurrentDataDTO d1;


    @BeforeAll
    static void beforeAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherService ws = new WeatherService(objectMapper);
        CityService cs = new CityService(objectMapper);

        activityService = new ActivityService(ws, cs);
    }

    @BeforeEach
    void setUp() {
        List<MunicipalityDTO> municipalityDTOList = new ArrayList<>();
        MunicipalityDTO m1 = new MunicipalityDTO("0265", "Roskilde");
        municipalityDTOList.add(m1);

        c1 = new CityInfoDTO("12337669-dbab-6b98-e053-d480220a5a3f", "Roskilde", municipalityDTOList);

        d1 = new CurrentDataDTO(30.0, "Rather cloudy", 90, "5.6 m/s");
        w1 = new WeatherInfoDTO("Roskilde", d1);

        a1 = new ActivityDTO(LocalDate.now(), "Run", LocalTime.now(), Duration.ofMinutes(30), 5.0, "No comment", w1, c1);
    }

    @Test
    void createActivity() {
        ActivityDTO expected = a1;
        ActivityDTO actual = activityService.createActivity(expected.getExcersizeDate(), expected.getExcersizeType(), expected.getTimeOfDay(), expected.getDuration(), expected.getDistance(), expected.getComment(), expected.getCityInfoDTO().getCityName());
        assertEquals(expected, actual);
    }
}