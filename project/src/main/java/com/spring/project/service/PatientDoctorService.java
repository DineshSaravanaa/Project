package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.Doctor;
import com.spring.project.model.Patient;
import com.spring.project.model.PatientDoctor;
import com.spring.project.repository.PatientDoctorRepository;

@Service
public class PatientDoctorService {

	@Autowired
	private PatientDoctorRepository patientDoctorRepository;
	@Autowired
	private DoctorService doctorService;

	public PatientDoctor addAppointment(PatientDoctor appointemt) {
		
		return patientDoctorRepository.save(appointemt);
	}

	public List<Patient> getAllPatientByDoctor(int doctorid) throws InvalidIDException {
		Doctor doctor = doctorService.getDoctorByID(doctorid);
		List<PatientDoctor> list = patientDoctorRepository.findByDoctorId(doctorid);
		return list.stream().map(patientDoctor -> patientDoctor.getPatient()).toList();
	}
	
	
}
