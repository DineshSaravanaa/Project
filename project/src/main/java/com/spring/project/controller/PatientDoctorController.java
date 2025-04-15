package com.spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.Doctor;
import com.spring.project.model.Patient;
import com.spring.project.model.PatientDoctor;
import com.spring.project.service.DoctorService;
import com.spring.project.service.PatientDoctorService;
import com.spring.project.service.PatientService;

@RestController
@RequestMapping("/api/appointments")
public class PatientDoctorController {
	
	@Autowired
	private PatientDoctorService patientDoctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	
	//2.Make Appointment For Patient With Doctor
	@PostMapping("/add/{patientid}/{doctorid}")
	public PatientDoctor addAppointment(@PathVariable int patientid,@PathVariable int doctorid,@RequestBody PatientDoctor appointemt) throws InvalidIDException {
		Patient patient= patientService.getPatientByID(patientid);
		Doctor doctor = doctorService.getDoctorByID(doctorid);
		appointemt.setPatient(patient);
		appointemt.setDoctor(doctor);
		return patientDoctorService.addAppointment(appointemt);
	}

}
