package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import io.egen.api.entity.AverageWeather;
import io.egen.api.entity.Weather;
import io.egen.api.entity.Wind;

public interface WeatherRepository extends Repository<Weather, String> {

	public Weather save(Weather weather);

	public Wind save(Wind wind);

	@Query("SELECT DISTINCT(w.city) FROM Weather w")
	public List<String> findDistinctCity();

	@Query("select w from Weather w where w.timestamp=(select Max(we.timestamp) from Weather we where we.city=?1) AND w.city=?1")
	public Optional<Weather> findRecentWeatherForCity(String city);

	@Query("select w from Weather w where w.city=?1")
	public Optional<Weather> findIfCityIsPresent(String city);

	@Query("SELECT new io.egen.api.entity.AverageWeather (w.city,AVG(w.humidity), AVG(w.temperature), AVG(w.pressure)) FROM Weather w where w.city=?1 group by extract(day_hour from  w.timestamp)")
	public List<AverageWeather> findAverageWeatherForCity(String city);

	@Query("SELECT new io.egen.api.entity.AverageWeather (w.city,AVG(w.humidity), AVG(w.temperature), AVG(w.pressure)) FROM Weather w where w.city=?1 group by extract(DAY from  w.timestamp)")
	public List<AverageWeather> findDailyAverageWeatherForCity(String city);

	public boolean existsByCity(String city);

}
