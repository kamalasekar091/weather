package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.AverageWeather;
import io.egen.api.entity.Weather;

public interface WeatherRepository {
	
	public Weather create(Weather weather);
	
	public List<String> findCity();
	
	public Optional<Weather> findRecentWeatherForCity(String city);
	
	public Optional<Weather> findIfCityIsPresent(String city);
	
	public List<AverageWeather> findAverageWeatherForCity(String city);
	
	public List<AverageWeather> findDailyAverageWeatherForCity(String city);
}
