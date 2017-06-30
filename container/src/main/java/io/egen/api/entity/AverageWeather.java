package io.egen.api.entity;

public class AverageWeather {

	private String city;
	private double humidity;
	private double pressure;
	private double temperature;

	public AverageWeather(String city, double humidity, double pressure, double temperature) {
		super();
		this.city = city;
		this.humidity = humidity;
		this.pressure = pressure;
		this.temperature = temperature;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "AverageWeather [city=" + city + ", humidity=" + humidity + ", pressure=" + pressure + ", temperature="
				+ temperature + "]";
	}

}
