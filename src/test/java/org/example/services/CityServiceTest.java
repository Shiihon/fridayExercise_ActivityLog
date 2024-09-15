package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dtos.CityInfoDTO;
import org.example.dtos.MunicipalityDTO;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class CityServiceTest {
    private static CityService cityService;
    private CityInfoDTO c1;

    @BeforeAll
    static void beforeAll() {
        cityService = new CityService(new ObjectMapper());
    }

    @BeforeEach
    void setUp() {
        List<MunicipalityDTO> municipalityDTOList = new ArrayList<>();
        MunicipalityDTO m1 = new MunicipalityDTO("0265", "Roskilde");
        municipalityDTOList.add(m1);

        c1 = new CityInfoDTO("12337669-dbab-6b98-e053-d480220a5a3f", "Roskilde", municipalityDTOList);
    }

    @Test
    void getCityInfoFromApi() {
        CityInfoDTO expected = c1;
        CityInfoDTO actual = cityService.getCityInfoFromApi(c1.getCityName());
        Assertions.assertEquals(expected, actual);
    }

}