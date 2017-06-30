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
import io.egen.api.entity.AverageWeather;
import io.egen.api.entity.Weather;
import io.egen.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 
 * @author Kamala
 * Weather Controller receives the request from the client
 * Process request and return JSON format
 */


@RestController
@RequestMapping(value = URI.WEATHER)
@Api(tags = "weather")
public class WeatherController {
	
	
	// Auto wired Weather Service, by using property injection
	@Autowired
	private WeatherService weatherservice;

	/**
	 * 
	 * @param weather
	 * @return
	 * Create method consumes weather from mocker service
	 * Pass the weather object to service object
	 * Insert Data into Wind Table and then into Weather Table
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Saves Data to backend", notes = "Consumes posted data from the mocker and stores in database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Weather create(@RequestBody Weather weather) {
		
		return weatherservice.create(weather);
	}

	
	
	/**
	 * 
	 * @return
	 * findCity() method is used to find if the provided city is present in database
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = URI.CITIES)
	@ApiOperation(value = "Find Cities", notes = "Retrive the list of cities which have repoted wether in past")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code=404,message="NotFound"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<String> findCity() {
		return weatherservice.findCity();
	}
	
	
	/**
	 * 
	 * @param city
	 * @return
	 * findRecentWeatherForCity method consumes city as a String
	 * Pass that string to service
	 * Return Recent weather data
	 */

	@RequestMapping(method = RequestMethod.GET, value = URI.RECENT_WEATHER_CITY)
	@ApiOperation(value = "Recent Weather For City", notes = "Find the Recent weather of the provided city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code=404,message="NotFound"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Weather findRecentWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findRecentWeatherForCity(city);
	}

	
	/**
	 * 
	 * @param city
	 * @param property
	 * @return
	 * Consumes City and property as String
	 * Pass the property to service
	 * Returns the property as String
	 */
	@RequestMapping(method = RequestMethod.GET, value = URI.RECENT_WEATHER_PROPERTY)
	@ApiOperation(value = "Find Recent Weather Property", notes = "Find the Property in a recent weather reported by the city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code=404,message="NotFound"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public String findRecentWeatherPropertyForCity(@PathVariable("city") String city,
			@PathVariable("property") String property) {

		return weatherservice.findRecentWeatherPropertyForCity(city, property);

	}

	/**
	 * 
	 * @param city
	 * @return
	 * findHourlyAverageWeatherForCity method consumes input parameter city as a string
	 * Pass the string to service
	 * Returns List of AverageWeather Per Hour object in JSON format
	 */
	@RequestMapping(method = RequestMethod.GET, value = URI.AVERAGE_WEATHER_PER_HOUR)
	@ApiOperation(value = "Find Houry Average weather", notes = "Retrive hourly average weather for the given city")
	public List<AverageWeather> findHourlyAverageWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findAverageWeatherForCity(city);
	}

	/**
	 * 
	 * @param city
	 * @return
	 * findDailyAverageWeatherForCity method consumes input parameter city as a string
	 * Pass the string to service
	 * Returns List of AverageWeather Per Day object in JSON format
	 */
	@RequestMapping(method = RequestMethod.GET, value = URI.AVERAGE_WEATHER_PER_DAY)
	@ApiOperation(value = "Find Daily  Average weather", notes = "Retrive daily average weather for the given city")
	public List<AverageWeather> findDailyAverageWeatherForCity(@PathVariable("city") String city) {
		return weatherservice.findDailyAverageWeatherForCity(city);
	}
}
