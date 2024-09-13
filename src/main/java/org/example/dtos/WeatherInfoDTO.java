package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfoDTO {
    @JsonProperty("LocationName")
    private String LocationName;
    @JsonProperty("CurrentData")
    @EqualsAndHashCode.Exclude // når testen kører ignorerer / excluder den currentData, da den konstant ændrer sig.
    private CurrentDataDTO currentDataDTO;
}
