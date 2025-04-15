package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
