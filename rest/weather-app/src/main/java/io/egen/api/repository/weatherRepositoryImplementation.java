package io.egen.api.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Weather;
import io.egen.api.entity.Wind;

@Repository
public class weatherRepositoryImplementation implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Weather create(Weather weather) {
		return null;
	}

	@Override
	public List<String> findCity() {
		return null;

	}

	@Override
	public Weather findRecentWeatherForCity(String city) {
		return null;
	}

	
	@Override
	public List<Weather> findAverageWeatherForCity(String city) {
		return null;
	}

	@Override
	public List<Weather> findDailyAverageWeatherForCity(String city) {
		
		return null;
	}

}
