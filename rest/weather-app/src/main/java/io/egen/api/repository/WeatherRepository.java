package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.Weather;

public interface WeatherRepository {
	
	public Weather create(Weather weather);
	
	public List<String> findCity();
	
	public Weather findRecentWeatherForCity(String city);
	
	public List<Weather> findAverageWeatherForCity(String city);
	
	public List<Weather> findDailyAverageWeatherForCity(String city);
}
