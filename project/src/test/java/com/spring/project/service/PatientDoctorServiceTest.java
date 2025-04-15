package com.spring.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.project.enums.Speciality;
import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.Doctor;
import com.spring.project.model.MedicalHistory;
import com.spring.project.model.Patient;
import com.spring.project.model.PatientDoctor;
import com.spring.project.model.User;
import com.spring.project.repository.PatientDoctorRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PatientDoctorServiceTest {

	@InjectMocks
	private PatientDoctorService patientDoctorService;
	@Mock
	private DoctorService doctorService;
	@Mock
	private PatientDoctorRepository patientDoctorRepository;

	Patient patient1;
	Patient patient2;
	Doctor doctor1;
	Doctor doctor2;
	PatientDoctor patientDoctor1;
	PatientDoctor patientDoctor2;

	@BeforeEach
	public void init() {
		doctor1 = new Doctor(1, "Dr. Smith", Speciality.ORTHO, new User(1, "drsmith@example.com", "pass1", "DOCTOR"));
		doctor2 = new Doctor(2, "Dr. Jane", Speciality.PHYSICIAN, new User(2, "drjane@example.com", "pass2", "DOCTOR"));

		patient1 = new Patient(1, "John Doe", 30, new User(3, "johndoe@example.com", "pass3", "PATIENT"),
				new MedicalHistory(1, "Hypertension", 3, "Amlodipine 5mg daily"));

		patient2 = new Patient(2, "Alice Brown", 25, new User(4, "alice@example.com", "pass4", "PATIENT"),
				new MedicalHistory(2, "Asthma", 5, "Salbutamol Inhaler"));
		
		patientDoctor1=new PatientDoctor(1,patient1,doctor1);
		patientDoctor2=new PatientDoctor(1,patient2,doctor1);
		
	}
	
	@Test
	public void getAllPatientByDoctorTest() throws InvalidIDException {
	    when(doctorService.getDoctorByID(1)).thenReturn(doctor1);
	    when(patientDoctorRepository.findByDoctorId(1)).thenReturn(Arrays.asList(patientDoctor1, patientDoctor2));

	    // correct doctor id
	    List<Patient> patientsForDoctor1 = patientDoctorService.getAllPatientByDoctor(1);
	    assertEquals(Arrays.asList(patient1, patient2), patientsForDoctor1);
	   
	    // incorrect doctor id
	    when(doctorService.getDoctorByID(5)).thenReturn(null); 
	    try {
	        patientDoctorService.getAllPatientByDoctor(5);
	    } catch (InvalidIDException e) {
	        assertEquals("Doctor ID Not Exist!", e.getMessage());
	    }

	    // Verifications
	    verify(doctorService, times(1)).getDoctorByID(1);
	    verify(doctorService, times(1)).getDoctorByID(5);
	    verify(patientDoctorRepository, times(1)).findByDoctorId(1);
	}

	}











