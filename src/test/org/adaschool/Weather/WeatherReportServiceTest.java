package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    public void testGetWeatherReport() {
        double latitude = 37.8267;
        double longitude = -122.4233;

        WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(50.0);
        weatherApiResponse.setMain(main);

        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(weatherApiResponse);

        WeatherReport result = weatherReportService.getWeatherReport(latitude, longitude);

        // Verifica que los métodos adecuados se hayan llamado con los argumentos correctos
        verify(restTemplate).getForObject(Mockito.anyString(), Mockito.eq(WeatherApiResponse.class));

        // Puedes realizar más aserciones según la estructura de tu respuesta
        assertThat(result.getTemperature()).isEqualTo(25.0);
        assertThat(result.getHumidity()).isEqualTo(50.0);
    }
}