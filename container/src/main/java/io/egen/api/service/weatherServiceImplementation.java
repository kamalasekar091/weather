package io.egen.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.api.entity.AverageWeather;
import io.egen.api.entity.Weather;
import io.egen.api.entity.Wind;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.WeatherRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Kamala weatherServiceImplementation is a service layer implementation
 *         Receives the input from the Controller and pass the received input to
 *         Repository
 * 
 */
@Service
public class weatherServiceImplementation implements WeatherService {

	// Autowired the Repository dependency
	@Autowired
	private WeatherRepository weatherrepo;

	/**
	 * create(weather) method consumes weather object returns weather object
	 * after creation or else returns null
	 */
	@Override
	@Transactional
	public Weather create(Weather weather) {
		if (weather != null) {
			Wind newwind = new Wind();
			newwind.setDegree(weather.getWind().getDegree());
			newwind.setSpeed(weather.getWind().getSpeed());
			Wind returnwind = weatherrepo.save(newwind);
			weather.setWind(returnwind);
			return weatherrepo.save(weather);
		} else {

			return null;
		}
	}

	/**
	 * findcity() method is used to find the list of cities which had reported
	 * weather status returns the list of city in string list<String>
	 */
	@Override
	@Transactional
	public List<String> findCity() {
		//
		return weatherrepo.findDistinctCity();

	}

	/**
	 * findRecentWeatherForCity finds the recent weather properties of a
	 * provided city If the city is present in database, weather object for
	 * corresponding city is returned If the provided city is not present in the
	 * database a not found exception thrown
	 */
	@Override
	public Weather findRecentWeatherForCity(String city) {
		return weatherrepo.findRecentWeatherForCity(city)
				.orElseThrow(() -> new NotFoundException(city + " does not exist"));
	}

	/**
	 * findAverageWeatherForCity finds the average weather properties of a
	 * provided city If the city is present in database, List<AverageWeather>
	 * per Hour object for corresponding city is returned If the provided city
	 * is not present in the database a not found exception thrown
	 */
	@Override
	public List<AverageWeather> findAverageWeatherForCity(String city) {

		if (weatherrepo.existsByCity(city)) {
			return weatherrepo.findAverageWeatherForCity(city);
		}
		throw new BadRequestException(city + " does not exist");
	}

	/**
	 * findDailyAverageWeatherForCity finds the average weather properties of a
	 * provided city If the city is present in database, List<AverageWeather>
	 * per Day object for corresponding city is returned If the provided city is
	 * not present in the database a not found exception thrown
	 */
	@Override
	public List<AverageWeather> findDailyAverageWeatherForCity(String city) {

		if (weatherrepo.existsByCity(city)) {
			return weatherrepo.findDailyAverageWeatherForCity(city);
		}
		throw new BadRequestException(city + " does not exist");

	}

	@Override
	public String findRecentWeatherPropertyForCity(String city, String property) {

		if (weatherrepo.existsByCity(city)) {
			Weather retrivedWeather = weatherrepo.findRecentWeatherForCity(city)
					.orElseThrow(() -> new BadRequestException(property + " does not exist"));

			if (property.equalsIgnoreCase("pressure")) {
				return String.valueOf(retrivedWeather.getPressure());

			} else if (property.equalsIgnoreCase("humidity")) {
				return String.valueOf(retrivedWeather.getHumidity());

			} else if (property.equalsIgnoreCase("temperature")) {
				return String.valueOf(retrivedWeather.getTemperature());

			} else {
				/**
				 * Return exception sating that the given property is not
				 * present in entity
				 */
				throw new BadRequestException("Property " + property + " does not exist");
			}
		} else {
			throw new BadRequestException(city + " does not exist");
		}

	}

}
