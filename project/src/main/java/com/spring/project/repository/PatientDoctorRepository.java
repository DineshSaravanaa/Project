package com.spring.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer>{

	List<PatientDoctor> findByDoctorId(int doctorid);

}
