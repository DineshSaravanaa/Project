package com.spring.project.model;

import java.util.Objects;

import com.spring.project.enums.Speciality;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false , length = 255)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Speciality speciality;
	
	@OneToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor(int id, String name, Speciality speciality, User user) {
		super();
		this.id = id;
		this.name = name;
		this.speciality = speciality;
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, speciality, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return id == other.id && Objects.equals(name, other.name) && speciality == other.speciality
				&& Objects.equals(user, other.user);
	}
	
	
	
	
}
