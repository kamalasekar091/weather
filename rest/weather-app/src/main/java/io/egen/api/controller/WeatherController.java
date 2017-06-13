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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = URI.WEATHER)
@Api(tags = "weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherservice;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Saves Data to backend", notes = "Consumes posted data from the mocker and stores in database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Weather create(@RequestBody Weather weather) {
		return weatherservice.create(weather);
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.CITIES)
	@ApiOperation(value = "Find Cities", notes = "Retrive the list of cities which have repoted wether in past")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code=404,message="NotFound"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<String> findCity() {
		return weatherservice.findCity();
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.RECENT_WEATHER_CITY)
	@ApiOperation(value = "Recent Weather For City", notes = "Find the Recent weather of the provided city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code=404,message="NotFound"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Weather findRecentWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findRecentWeatherForCity(city);
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.RECENT_WEATHER_PROPERTY)
	@ApiOperation(value = "Find Recent Weather Property", notes = "Find the Property in a recent weather reported by the city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code=404,message="NotFound"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public String findRecentWeatherPropertyForCity(@PathVariable("city") String city,
			@PathVariable("property") String property) {

		return weatherservice.findRecentWeatherPropertyForCity(city, property);

	}

	// findAverageWeatherForCity
	@RequestMapping(method = RequestMethod.GET, value = URI.AVERAGE_WEATHER_PER_HOUR)
	@ApiOperation(value = "Find Houry Average weather", notes = "Retrive hourly average weather for the given city")
	public List<Weather> findHourlyAverageWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findAverageWeatherForCity(city);
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.AVERAGE_WEATHER_PER_DAY)
	@ApiOperation(value = "Find Daily  Average weather", notes = "Retrive daily average weather for the given city")
	public List<Weather> findDailyAverageWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findDailyAverageWeatherForCity(city);
	}
}
