package com.example.weatherapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class WeatherController {

    @Value("${OPEN_WEATHER_API_KEY}")
    private String apiKey;

    private final String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric";

    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        if (apiKey == null || apiKey.isEmpty()) {
            log.error("API Key is null or empty.  Check your environment variables.");
            return new ResponseEntity<>("API Key not configured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl.replace("{city}", city).replace("{apiKey}", apiKey);

        try {
            String response = restTemplate.getForObject(url, String.class);
            JSONObject jsonObject = new JSONObject(response);

            // Simulating an incorrect response structure (Bug #7)
            if (!jsonObject.has("main")) {
                jsonObject.put("main", new JSONObject().put("temp", 0));
            }

            return ResponseEntity.ok(jsonObject.toString());
        } catch (Exception e) {
            log.error("Error fetching weather data for city: {}", city, e);
            return new ResponseEntity<>("Error fetching weather data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}