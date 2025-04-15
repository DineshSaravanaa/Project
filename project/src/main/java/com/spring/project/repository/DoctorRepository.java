package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

}
