package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

@Entity
public class Wind {

	@Id
	private String Id;
	private long speed;
	private long degree;
	
	public Wind(){
		this.Id=UUID.randomUUID().toString();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public long getDegree() {
		return degree;
	}

	public void setDegree(long degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return String.format("Wind [Id=%s, speed=%s, degree=%s]", Id, speed, degree);
	}
	
	
}
