package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Activity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "excersize_date", nullable = false)
    private LocalDate excersizeDate;

    @Column(name = "excersize_type")
    private String excersizeType;

    @Column(name = "time_of_day")
    private LocalTime timeOfDay;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "comment")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "city_info")
    private CityInfo cityInfo;

    @OneToOne
    @JoinColumn(name = "weather_id", referencedColumnName = "id")
    private WeatherInfo weatherInfo;
}
