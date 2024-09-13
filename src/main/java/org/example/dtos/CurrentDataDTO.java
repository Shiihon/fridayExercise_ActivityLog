package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentDataDTO {
private Double temperature;
private String skyText;
private int humidity; //ASK! Always null.
private String windText;
}
