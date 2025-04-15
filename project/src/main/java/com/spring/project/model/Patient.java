package com.spring.project.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false , length = 255)
	private String name;
	
	@Column(nullable = false)
	private int age;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private MedicalHistory medicalHistory;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public Patient(int id, String name, int age, User user, MedicalHistory medicalHistory) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.user = user;
		this.medicalHistory = medicalHistory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, medicalHistory, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return age == other.age && id == other.id && Objects.equals(medicalHistory, other.medicalHistory)
				&& Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}
	
	
	
	
	
	
}
