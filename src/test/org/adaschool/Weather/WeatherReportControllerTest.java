package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(org.adaschool.Weather.controller.WeatherReportController.class)
public class WeatherReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @Test
    public void testGetWeatherReport() throws Exception {
        double latitude = 37.8267;
        double longitude = -122.4233;

        // Puedes personalizar esto según la estructura de tu respuesta
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setTemperature(25.0);
        weatherReport.setHumidity(50.0);

        Mockito.when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(weatherReport);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/weather-report")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(50.0));
    }
}