package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "municipality")
public class Municipality {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "municipality_id")
    private String municipalityId;

    @Column(name = "municipality_name")
    private String name;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CityInfo cityInfo;
}
