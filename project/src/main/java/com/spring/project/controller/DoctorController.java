package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.Doctor;
import com.spring.project.model.Patient;
import com.spring.project.model.User;
import com.spring.project.service.AuthService;
import com.spring.project.service.DoctorService;
import com.spring.project.service.PatientDoctorService;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AuthService authService;
	@Autowired
	private PatientDoctorService patientDoctorService;
	
	@PostMapping("/add/{userid}")
	public Doctor addDoctor(@PathVariable int userid,@RequestBody Doctor doctor) throws InvalidIDException {
		User user=authService.getUserById(userid);
		doctor.setUser(user);
		return doctorService.addDoctor(doctor);
	}
	
	// 3. Get All Patients By Doctor ID
	@GetMapping("/getallpatient/{doctorid}")
	public List<Patient> getAllPatientByDoctor(@PathVariable int doctorid) throws InvalidIDException {
		return patientDoctorService.getAllPatientByDoctor(doctorid);
		
	}
	

}












