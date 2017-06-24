package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.AverageWeather;
import io.egen.api.entity.Weather;

public interface WeatherService {
	
	public Weather create(Weather weather);
	
	public List<String> findCity();
	
	public Weather findRecentWeatherForCity(String city);
	
	public List<AverageWeather> findAverageWeatherForCity(String city);
	
	public List<AverageWeather> findDailyAverageWeatherForCity(String city);
	
	public String findRecentWeatherPropertyForCity(String city, String property);

}
