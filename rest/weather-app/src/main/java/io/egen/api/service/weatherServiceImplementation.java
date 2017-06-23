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

@Service
public class weatherServiceImplementation implements WeatherService {

	@Autowired
	private WeatherRepository weatherrepo;

	@Override
	@Transactional
	public Weather create(Weather weather) {
		if (weather != null) {
			return weatherrepo.create(weather);
		} else {
			/**
			 * Return an exception
			 */
			return null;
		}
	}

	@Override
	@Transactional
	public List<String> findCity() {
		//
		return weatherrepo.findCity();
	}

	@Override
	public Weather findRecentWeatherForCity(String city) {

		
		
		//Weather theWeather = weatherrepo.findIfCityIsPresent(city).orElseThrow(()-> new NotFoundException(city + " does not exist"));

		return weatherrepo.findRecentWeatherForCity(city).orElseThrow(()-> new NotFoundException(city + " does not exist"));
		
//		Weather theWeather = weatherrepo.findIfCityIsPresent(city);
//		
//		if (theWeather != null) {
//			return weatherrepo.findRecentWeatherForCity(city);
//		} else {
//			/*
//			 * Return a exception stating that the provided city does not exists
//			 * in the database
//			 */
//			throw new NotFoundException(city + " does not exist");
//		}

	}

	@Override
	public List<AverageWeather> findAverageWeatherForCity(String city) {
		
		
		Weather theWeather = weatherrepo.findIfCityIsPresent(city).orElseThrow(()-> new NotFoundException(city + " does not exist"));

		return weatherrepo.findAverageWeatherForCity(city);


//		Weather theWeather = weatherrepo.findIfCityIsPresent(city);
//
//		if (theWeather != null) {
//			return weatherrepo.findAverageWeatherForCity(city);
//		} else {
//			/*
//			 * Return a exception stating that the provided city does not exists
//			 * in the database
//			 */
//			throw new NotFoundException(city + " does not exist");
//		}

	}
	@Override
	public List<AverageWeather> findDailyAverageWeatherForCity(String city) {
		
		Weather theWeather = weatherrepo.findIfCityIsPresent(city).orElseThrow(()-> new NotFoundException(city + " does not exist"));

		return weatherrepo.findDailyAverageWeatherForCity(city);

		
//		Weather theWeather = weatherrepo.findIfCityIsPresent(city);
//
//		if (theWeather != null) {
//			return weatherrepo.findDailyAverageWeatherForCity(city);
//		} else {
//			/*
//			 * Return a exception stating that the provided city does not exists
//			 * in the database
//			 */
//			throw new NotFoundException(city + " does not exist");
//		}

	}

	@Override
	public String findRecentWeatherPropertyForCity(String city, String property) {

		Weather theWeather = weatherrepo.findIfCityIsPresent(city).orElseThrow(()-> new NotFoundException(city + " does not exist"));
		
		Weather retrivedWeather = weatherrepo.findRecentWeatherForCity(city).orElseThrow(()-> new BadRequestException(property + " does not exist"));
		//Weather theWeather = weatherrepo.findIfCityIsPresent(city);

		if (theWeather != null) {

		//	Weather retrivedWeather = weatherrepo.findRecentWeatherForCity(city).orElseThrow(()-> new BadRequestException(property + " does not exist"));

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
