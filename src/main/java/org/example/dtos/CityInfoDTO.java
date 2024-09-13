package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityInfoDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("primærtnavn")
    private String cityName;
    @JsonProperty("kommuner")
    private List<MunicipalityDTO> municipalities;
}
