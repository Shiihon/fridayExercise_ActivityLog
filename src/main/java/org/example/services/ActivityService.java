package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.daos.ActivityDAO;
import org.example.dtos.ActivityDTO;
import org.example.dtos.CityInfoDTO;
import org.example.dtos.WeatherInfoDTO;
import org.example.entities.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityService {
    private WeatherService weatherService;
    private CityService cityService;
    private final ActivityDAO dao;

    public ActivityService(WeatherService weatherService, CityService cityService, EntityManagerFactory emf) {
        this.weatherService = weatherService;
        this.cityService = cityService;
        this.dao = new ActivityDAO(emf);
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

    public Activity persistActivity(ActivityDTO activityDTO) {
        Activity activity = Activity.builder()
                .excersizeType(activityDTO.getExcersizeType())
                .timeOfDay(activityDTO.getTimeOfDay())
                .duration(activityDTO.getDuration())
                .distance(activityDTO.getDistance())
                .comment(activityDTO.getComment())
                .excersizeDate(activityDTO.getExcersizeDate())
                .cityInfo(convertCityInfoToEntity(activityDTO.getCityInfoDTO()))
                .weatherInfo(convertWeatherInfoToEntity(activityDTO.getWeatherInfo()))
                .build();

        return dao.create(activity);
    }

    public CityInfo convertCityInfoToEntity(CityInfoDTO dto) {
        CityInfo cityInfo = CityInfo.builder()
                .cityName(dto.getCityName())
                .cityId(dto.getId())
                .build();

        //for hver municipality dto, konverteres hver kommune dto til en entityDTO.
        dto.getMunicipalities()
                .forEach(municipalityDTO -> {
                    Municipality municipality = Municipality.builder()
                            .municipalityId(municipalityDTO.getId())
                            .name(municipalityDTO.getName())
                            .build();

                    cityInfo.getMunicipalities().add(municipality); // efter de er blevet lavet addes de.
                });

        return cityInfo;
    }

    public WeatherInfo convertWeatherInfoToEntity(WeatherInfoDTO dto) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setLocationName(dto.getLocationName());

        if (dto.getCurrentDataDTO() != null) {
            CurrentData currentData = new CurrentData();
            currentData.setHumidity(dto.getCurrentDataDTO().getHumidity());
            currentData.setTemperature(dto.getCurrentDataDTO().getTemperature());
            currentData.setSkyText(dto.getCurrentDataDTO().getSkyText());
            currentData.setWindText(dto.getCurrentDataDTO().getWindText());

            currentData.setWeatherInfo(weatherInfo);
            weatherInfo.setCurrentData(currentData);
        }
        return weatherInfo;
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("activity_logging");

        WeatherService weatherService = new WeatherService(objectMapper);
        CityService cityService = new CityService(objectMapper);

//        List<MunicipalityDTO> municipalityDTOList = new ArrayList<>();
//        MunicipalityDTO m1 = new MunicipalityDTO("0265", "Roskilde");
//        municipalityDTOList.add(m1);
//
//        CityInfoDTO c1 = new CityInfoDTO("12337669-dbab-6b98-e053-d480220a5a3f", "Roskilde", municipalityDTOList);
//
//        CurrentDataDTO d1 = new CurrentDataDTO(30.0, "Rather cloudy", 90, "5.6 m/s");
//        WeatherInfoDTO w1 = new WeatherInfoDTO("Roskilde", d1);
//
//        ActivityDTO a1 = new ActivityDTO(LocalDate.now(), "Run", LocalTime.now(), Duration.ofMinutes(30), 5.0, "No comment", w1, c1);
//        ActivityService activityService = new ActivityService(weatherService, cityService, emf);
//        activityService.persistActivity(a1);

        ActivityService activityService = new ActivityService(weatherService, cityService, emf);
        ActivityDTO createdAct = activityService.createActivity(LocalDate.now(), "Run", LocalTime.now(), Duration.ofMinutes(30), 5.0, "No comment", "Roskilde");

        activityService.persistActivity(createdAct);
    }
}
