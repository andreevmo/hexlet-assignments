package exercise.service;

import exercise.HttpClient;
import org.springframework.stereotype.Service;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public String getWeather(String city) {
        return client.get("http://weather/api/v2/cities/" + city);
    }
    // END
}
