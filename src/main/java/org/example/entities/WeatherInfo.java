package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "weather_info")
public class WeatherInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "location_name")
    private String LocationName;

    @OneToOne
    @JoinColumn(name = "current_data_id", referencedColumnName = "id")
    private CurrentData currentData;

    @OneToOne(mappedBy = "weatherInfo")
    private Activity activity;
}
