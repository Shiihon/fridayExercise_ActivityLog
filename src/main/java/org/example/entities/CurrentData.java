package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "current_data")
public class CurrentData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Double temperature;
    @Column(name = "sky_text")
    private String skyText;

    @Column(name = "humidity")
    private int humidity; //ASK! Always null.

    @Column(name = "wind_text")
    private String windText;

    @OneToOne(mappedBy = "currentData")
    private WeatherInfo weatherInfo;
}
