package com.spring.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.MedicalHistory;
import com.spring.project.model.Patient;
import com.spring.project.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	public Patient addPatient(Patient patient) {

		return patientRepository.save(patient);
	}

	public Patient getPatientByID(int patientid) throws InvalidIDException {
		Optional<Patient> optional = patientRepository.findById(patientid);
		if (optional.isEmpty())
			throw new InvalidIDException("Patient ID Not Exist!");

		return optional.get();
	}

}
