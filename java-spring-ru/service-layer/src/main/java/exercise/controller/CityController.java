package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public String getWeatherInCity(@PathVariable long id, HttpServletResponse response) {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new CityNotFoundException("City with such id not found"));
        response.addHeader("Content-Type", "application/json");
        return weatherService.getWeather(city.getName());
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getWeatherInCities(@RequestParam(required = false) String name) {
        Iterable<City> cities = (name == null ? cityRepository.findAllOrderByName()
                        : cityRepository.findByNameStartingWithIgnoreCase(name));
        List<Map<String, String>> response = new ArrayList<>();
        for (City c : cities) {
            String weather = weatherService.getWeather(c.getName());
            Map<String, String> weatherMap;
            try {
                weatherMap = new ObjectMapper().readValue(weather, Map.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            response.add(Map.of("temperature", weatherMap.get("temperature"), "name", c.getName()));
        }
        return response;
    }
    // END
}

