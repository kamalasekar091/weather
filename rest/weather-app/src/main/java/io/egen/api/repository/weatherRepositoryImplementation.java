package io.egen.api.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.egen.api.entity.Weather;
import io.egen.api.entity.Wind;

@Repository
public class weatherRepositoryImplementation implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	
	//private Wind newwind;

	@Override
	public Weather create(Weather weather) {
		//Try changing this
		Wind newwind= new Wind();
		newwind.setDegree(weather.getWind().getDegree());
		newwind.setSpeed(weather.getWind().getSpeed());
		em.persist(newwind);
		weather.setWind(newwind);
		em.persist(weather);

		return weather;
	}

	@Override
	public List<String> findCity() {
		// Return list of cities
		Query query = em.createNamedQuery("Weather.distinctCity", String.class);

		return query.getResultList();

	}

	@Override
	public Weather findRecentWeatherForCity(String city) {
		// return recent weather of the city
		TypedQuery<Weather> query = em.createNamedQuery("Weather.recentWeather", Weather.class);
		query.setParameter("pCity", city);
		List<Weather> listweather = query.getResultList();
		if (!listweather.isEmpty()) {
			return listweather.get(0);
		}
		return null;
	}

	@Override
	public List<Weather> findAverageWeatherForCity(String city) {
		// Return the list of the average weather
//		TypedQuery<Weather> typedquery = em.createNamedQuery("Weather.averagePerHour", Weather.class);
//		List staggedweather=typedquery.getResultList();
//		List<Weather> returnWeather=new ArrayList<Weather>();
//		for(Object result:staggedweather){
//			returnWeather.add(new Weather(result[0],result[1],result[2]));
//		}
//		
//		return returnWeather;
		
//		Query query = em.createNamedQuery("Weather.averagePerHour");
//		query.setParameter("pCity", city);
//		List<Object[]> results = query.getResultList();
//		List<Weather> listweather = new ArrayList<Weather>();
//		System.out.println("Size of the result list "+results.size());
//		for (Object[] result : results) {
//
//			// listweather.add(new
//			// Weather(Double.valueOf((double)result[0]),Long.valueOf((long)result[1]),Long.valueOf((long)result[2])));
//			//listweather.add(new Weather(Double.valueOf((double) result[0])));
//			System.out.println(Double.valueOf((double)result[0])+"-----------"+Double.valueOf((double)result[1])+"-----------"+Double.valueOf((double)result[2]));
//			 listweather.add(new Weather(Double.valueOf((double)result[0]),Double.valueOf((double)result[1]),Double.valueOf((double)result[2])));
//			
//		}
//		
//
//		return typedquery.getResultList();

	}

	@Override
	public List<Weather> findDailyAverageWeatherForCity(String city) {
		// Return the list of the average weather
				Query query = em.createNamedQuery("Weather.averagePerDay");
				query.setParameter("pCity", city);
				List<Object[]> results = query.getResultList();
				List<Weather> listweather = new ArrayList<Weather>();
				for (Object[] result : results) {
					 System.out.println(Double.valueOf((double)result[0])+"-----------"+Double.valueOf((double)result[1])+"-----------"+Double.valueOf((double)result[2]));
					 listweather.add(new Weather(Double.valueOf((double)result[0]),Double.valueOf((double)result[1]),Double.valueOf((double)result[2])));
					//listweather.add(new Weather(Double.valueOf((double) result[0])));
				}

				return listweather;
	}

	@Override
	public Weather findIfCityIsPresent(String city) {
		// Find if the provided city exists in database
		TypedQuery<Weather> query = em.createNamedQuery("Weather.checkForCity", Weather.class);
		query.setParameter("pCity", city);
		List<Weather> listweather = query.getResultList();
		if (!listweather.isEmpty()) {
			return listweather.get(0);
		}
		return null;
	}

}
