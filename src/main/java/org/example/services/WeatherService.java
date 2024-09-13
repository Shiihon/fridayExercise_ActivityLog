package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dtos.WeatherInfoDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    private ObjectMapper objectMapper;

    public WeatherService(ObjectMapper objectMapper) {
        this.objectMapper = new ObjectMapper();
    }

    public WeatherInfoDTO getWeather(String location) {
        try {

            //Create http client
            HttpClient httpClient = HttpClient.newHttpClient();

            String baseUri = "https://vejr.eu/api.php?location=";

            StringBuilder builder = new StringBuilder(baseUri)
                    .append(location)
                    .append("&degree=C");

            //Create a request
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .header("Accept", "application/json")
                    .uri(URI.create(builder.toString()))
                    .GET()
                    .build();

            //Send request and get response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the status code and print the response
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), WeatherInfoDTO.class);
            } else {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
