package io.egen.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.entity.Weather;


@RestController
@RequestMapping(value = "weather")
public class WeatherController {

	

	@RequestMapping(method = RequestMethod.POST)
	public Weather create(@RequestBody Weather weather) {
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "cities") 
	public List<String> findCity() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "recent-weather/{city}") 
	public Weather findRecentWeatherForCity(@PathVariable("city") String city) {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "recent-weather/{city}/{property}") 
	public String findRecentWeatherPropertyForCity(@PathVariable("city") String city,@PathVariable("property") String property) {
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "average-weather-per-hour/{city}") 
	public List<Weather> findAverageWeatherForCity(@PathVariable("city") String city) {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "average-weather-per-day/{city}") 
	public List<Weather> findDailyAverageWeatherForCity(@PathVariable("city") String city) {
		return null;
	}
	
}
