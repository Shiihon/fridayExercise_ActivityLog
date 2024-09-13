package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dtos.CityInfoDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CityService {
    private ObjectMapper objectMapper;

    public CityService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CityInfoDTO getCityInfoFromApi(String cityName) {

        try {
            //Create http client
            HttpClient httpClient = HttpClient.newHttpClient();

            String baseUri = "https://dawa.aws.dk/steder?hovedtype=Bebyggelse&undertype=by&primærtnavn=";

            StringBuilder builder = new StringBuilder(baseUri)
                    .append(cityName);

            //Create a request
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .header("Accept", "application/json")
                    .uri(URI.create(builder.toString()))
                    .GET()
                    .build();

            //Send request and get response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the status code and print the response
            if (response.statusCode() == 200) {
                CityInfoDTO[] cities = objectMapper.readValue(response.body(), CityInfoDTO[].class);

                if (cities.length > 0) {
                    return cities[0]; //return first object in the array.
                } else {
                    System.out.println("No city information found for : " + cityName);
                }

            } else {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}


