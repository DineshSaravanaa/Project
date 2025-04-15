package com.spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.MedicalHistory;
import com.spring.project.model.Patient;
import com.spring.project.model.User;
import com.spring.project.service.AuthService;
import com.spring.project.service.MedicalHistoryService;
import com.spring.project.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private AuthService authService;
	@Autowired
	private MedicalHistoryService medicalHistoryService;
	
	// 1. API To Add Patient With Medical History With User Details
	@PostMapping("/add/{userid}/{medicalhistoryid}")
	public Patient addPatient(@PathVariable int userid,@PathVariable int medicalhistoryid,@RequestBody Patient patient) throws InvalidIDException {
		User user=authService.getUserById(userid);
		MedicalHistory medicalHistory=medicalHistoryService.getByID(medicalhistoryid);
		patient.setUser(user);
		patient.setMedicalHistory(medicalHistory);
		return patientService.addPatient(patient);
		
	}

}
