package org.example.dtos;

import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ActivityDTO {
    private LocalDate excersizeDate;
    private String excersizeType;
    private LocalTime timeOfDay;
    private Duration duration;
    private Double distance;
    private String comment;
    private WeatherInfoDTO weatherInfo;
    private CityInfoDTO cityInfoDTO;
}
