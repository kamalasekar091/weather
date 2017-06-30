package io.egen.api.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.egen.api.serializer.AuDateSerializer;

@Entity
public class Weather {

	@Id
	private String id;
	private String city;
	private String description;
	private double humidity;
	private double pressure;
	private double temperature;
	@OneToOne
	private Wind wind;

	@JsonSerialize(using = AuDateSerializer.class)
	private java.util.Date timestamp;

	public Weather() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", city=" + city + ", description=" + description + ", humidity=" + humidity
				+ ", pressure=" + pressure + ", wind=" + wind + ", timestamp=" + timestamp + "]";
	}

}
