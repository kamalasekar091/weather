package io.egen.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.egen.api.entity.Weather;

import org.springframework.transaction.annotation.Transactional;

@Service
public class weatherServiceImplementation implements WeatherService {

	@Override
	@Transactional
	public Weather create(Weather weather) {

		return null;
	}

	@Override
	@Transactional
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
