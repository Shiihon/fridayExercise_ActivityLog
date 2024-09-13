package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipalityDTO {
    @JsonProperty("kode")
    private String id;
    @JsonProperty("navn")
    private String name;
}
