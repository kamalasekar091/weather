package io.egen.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.api.entity.AverageWeather;
import io.egen.api.entity.Weather;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.WeatherRepository;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author Kamala
 * weatherServiceImplementation is a service layer implementation
 * Receives the input from the Controller and pass the received input to Repository
 * 
 */
@Service
public class weatherServiceImplementation implements WeatherService {

	// Autowired the Repository dependency
	@Autowired
	private WeatherRepository weatherrepo;
	
	/**
	 * create(weather) method consumes weather object
	 * returns weather object after creation
	 * or else returns null 
	 */
	@Override
	@Transactional
	public Weather create(Weather weather) {
		if (weather != null) {
			return weatherrepo.create(weather);
		} else {

			return null;
		}
	}
	
	/**
	 * findcity() method is used to find the list of cities which had reported weather status
	 * returns the list of city in string list<String>
	 */
	@Override
	@Transactional
	public List<String> findCity() {
		//
		return weatherrepo.findCity();
	}

	/**
	 * findRecentWeatherForCity finds the recent weather properties of a provided city
	 * If the city is present in database, weather object for corresponding city is returned
	 * If the provided city is not present in the database a not found exception thrown
	 */
	@Override
	public Weather findRecentWeatherForCity(String city) {
		return weatherrepo.findRecentWeatherForCity(city)
				.orElseThrow(() -> new NotFoundException(city + " does not exist"));
	}

	/**
	 * findAverageWeatherForCity finds the average weather properties of a provided city
	 * If the city is present in database, List<AverageWeather> per Hour object for corresponding city is returned
	 * If the provided city is not present in the database a not found exception thrown
	 */
	@Override
	public List<AverageWeather> findAverageWeatherForCity(String city) {

		weatherrepo.findIfCityIsPresent(city)
				.orElseThrow(() -> new NotFoundException(city + " does not exist"));
		
		return weatherrepo.findAverageWeatherForCity(city);

	}

	/**
	 * findDailyAverageWeatherForCity finds the average weather properties of a provided city
	 * If the city is present in database, List<AverageWeather> per Day object for corresponding city is returned
	 * If the provided city is not present in the database a not found exception thrown
	 */
	@Override
	public List<AverageWeather> findDailyAverageWeatherForCity(String city) {

		weatherrepo.findIfCityIsPresent(city)
				.orElseThrow(() -> new NotFoundException(city + " does not exist"));

		return weatherrepo.findDailyAverageWeatherForCity(city);

	}

	@Override
	public String findRecentWeatherPropertyForCity(String city, String property) {

		Weather theWeather = weatherrepo.findIfCityIsPresent(city)
				.orElseThrow(() -> new NotFoundException(city + " does not exist"));

		Weather retrivedWeather = weatherrepo.findRecentWeatherForCity(city)
				.orElseThrow(() -> new BadRequestException(property + " does not exist"));
		// Weather theWeather = weatherrepo.findIfCityIsPresent(city);

		if (theWeather != null) {

			// Weather retrivedWeather =
			// weatherrepo.findRecentWeatherForCity(city).orElseThrow(()-> new
			// BadRequestException(property + " does not exist"));

			if (property.equalsIgnoreCase("pressure")) {
				String propertyFromResult = String.valueOf(retrivedWeather.getPressure());
				return propertyFromResult;
			} else if (property.equalsIgnoreCase("humidity")) {
				String propertyFromResult = String.valueOf(retrivedWeather.getHumidity());
				return propertyFromResult;
			} else if (property.equalsIgnoreCase("temperature")) {
				String propertyFromResult = String.valueOf(retrivedWeather.getTemperature());
				return propertyFromResult;
			} else {
				/**
				 * Return exception sating that the given property is not
				 * present in entity
				 */
				throw new BadRequestException(property + " does not exist");
			}
		} else {
			/*
			 * Return a exception stating that the provided city does not exists
			 * in the database
			 */
			throw new NotFoundException(city + " does not exist");

		}
	}

}
