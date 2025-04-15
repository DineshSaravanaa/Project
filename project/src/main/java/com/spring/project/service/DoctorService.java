package com.spring.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.Doctor;
import com.spring.project.model.MedicalHistory;
import com.spring.project.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor addDoctor(Doctor doctor) {

		return doctorRepository.save(doctor);
	}

	public Doctor getDoctorByID(int doctorid) throws InvalidIDException {
		Optional<Doctor> optional = doctorRepository.findById(doctorid);
		if (optional.isEmpty())
			throw new InvalidIDException("Doctor ID Not Exist!");

		return optional.get();
	}
}
