package io.egen.api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.egen.api.entity.AverageWeather;
import io.egen.api.entity.Weather;
import io.egen.api.entity.Wind;

/**
 * 
 * @author Kamala
 * weatherRepositoryImplementation Class is the Implementation weatherRepository Interface
 * Repository is used communicate to database and perform Insert and Select operation
 */
@Repository
public class weatherRepositoryImplementation implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	//create method consumes weather object supplied from service
	//Perform insertion operation
	//Returns Weather object
	@Override
	public Weather create(Weather weather) {
		// Create a wind object to persist the wind in database
		Wind newwind = new Wind();
		newwind.setDegree(weather.getWind().getDegree());
		newwind.setSpeed(weather.getWind().getSpeed());
		//Persist Wind Object
		em.persist(newwind);
		//Set the wind object in Weather object
		weather.setWind(newwind);
		//Insert weather data in weather table
		em.persist(weather);

		return weather;
	}

	//findcity() is used to retrieve the value current cities which have reported the weather status
	@Override
	public List<String> findCity() {
		// Query to return distinct weather in the tables
		Query query = em.createNamedQuery("Weather.distinctCity", String.class);

		return query.getResultList();

	}
	
	
	//FindRecentWeatherForCity(city) method is used find and return the recent weather for the provided city
	@Override
	public Optional<Weather> findRecentWeatherForCity(String city) {
		// return recent weather of the city
		TypedQuery<Weather> query = em.createNamedQuery("Weather.recentWeather", Weather.class);
		//set the property of city in the query
		query.setParameter("pCity", city);
		//Create a listweather to hold the result of the list
		List<Weather> listweather = query.getResultList();
		if (!listweather.isEmpty()) {
			//return the first value
			return Optional.of(listweather.get(0));
		}
		return Optional.empty();
	}

	//findAverageWeatherForCity(city) finds the daily-hourly average weather
	//returns the list of average per hour
	@Override
	public List<AverageWeather> findAverageWeatherForCity(String city) {
		// Return the list of the average weather
		Query query = em.createNamedQuery("Weather.averagePerHour");
		query.setParameter("pCity", city);
		List<Object[]> results = query.getResultList();
		List<AverageWeather> listweather = new ArrayList<AverageWeather>();
		System.out.println("Size of the result list " + results.size());
		for (Object[] result : results) {
			listweather.add(new AverageWeather(String.valueOf(result[0]), Double.valueOf((double) result[1]),
					Double.valueOf((double) result[2]), Double.valueOf((double) result[3])));
		}

		return listweather;

	}

	//findDailyAverageWeatherForCity(city) finds the daily average weather
	//returns the list of average per day
	@Override
	public List<AverageWeather> findDailyAverageWeatherForCity(String city) {
		// Return the list of the average weather
		Query query = em.createNamedQuery("Weather.averagePerDay");
		query.setParameter("pCity", city);
		List<Object[]> results = query.getResultList();
		List<AverageWeather> listweather = new ArrayList<AverageWeather>();
		for (Object[] result : results) {
			listweather.add(new AverageWeather(String.valueOf(result[0]), Double.valueOf((double) result[1]),
												Double.valueOf((double) result[2]), Double.valueOf((double) result[3])));
			
		}

		return listweather;
	}

	//findIfCityIsPresent(city) finds if the given city is present in database
	@Override
	public Optional<Weather> findIfCityIsPresent(String city) {
		// Find if the provided city exists in database
		TypedQuery<Weather> query = em.createNamedQuery("Weather.checkForCity", Weather.class);
		query.setParameter("pCity", city);
		List<Weather> listweather = query.getResultList();
		if (!listweather.isEmpty()) {
			return Optional.of(listweather.get(0));
		}
		return Optional.empty();
	}

}
