package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city_info")
public class CityInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "city_info_id")
    private String cityId;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "cityInfo", fetch = FetchType.EAGER)
    private Set<Municipality> municipalities;

    @OneToMany(mappedBy = "cityInfo")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Activity> activities;

    @Builder
    public CityInfo(String cityId, String cityName, Set<Activity> activities) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.activities = activities;
        this.municipalities = new HashSet<>();
    }

    //lav metode til at adde municipality, og til activity.
}
