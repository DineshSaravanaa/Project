package com.spring.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.model.MedicalHistory;
import com.spring.project.repository.MedicalHistoryRepository;

@Service
public class MedicalHistoryService {
	
	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;

	public MedicalHistory addHistory(MedicalHistory request) {
		
		return medicalHistoryRepository.save(request);
	}
	
	public MedicalHistory getByID(int id) throws InvalidIDException {
		Optional<MedicalHistory> optional =  medicalHistoryRepository.findById(id);
		if(optional.isEmpty())
			throw new InvalidIDException("MedicalHistory ID Not Exist!");
			
		return optional.get();
	}
	
	

}
