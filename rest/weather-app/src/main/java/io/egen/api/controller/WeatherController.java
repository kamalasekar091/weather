package io.egen.api.controller;

import java.util.List;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.constants.URI;
import io.egen.api.entity.Weather;
import io.egen.api.service.WeatherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = URI.WEATHER)

public class WeatherController {

	@Autowired
	private WeatherService weatherservice;

	@RequestMapping(method = RequestMethod.POST)
	public Weather create(@RequestBody Weather weather) {
		return weatherservice.create(weather);
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.CITIES)
	public List<String> findCity() {
		return weatherservice.findCity();
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.RECENT_WEATHER_CITY)
	public Weather findRecentWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findRecentWeatherForCity(city);
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.RECENT_WEATHER_PROPERTY)
	public String findRecentWeatherPropertyForCity(@PathVariable("city") String city,
			@PathVariable("property") String property) {

		return weatherservice.findRecentWeatherPropertyForCity(city, property);

	}

	// findAverageWeatherForCity
	@RequestMapping(method = RequestMethod.GET, value = URI.AVERAGE_WEATHER_PER_HOUR)
	public List<Weather> findAverageWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findAverageWeatherForCity(city);
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.AVERAGE_WEATHER_PER_DAY)
	public List<Weather> findDailyAverageWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findDailyAverageWeatherForCity(city);
	}
}
