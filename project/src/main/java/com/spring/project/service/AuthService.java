package com.spring.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.exception.InvalidUsernameException;
import com.spring.project.model.User;
import com.spring.project.repository.AuthRepository;

@Service
public class AuthService {
	
	@Autowired
	private AuthRepository authRepository;
	@Autowired
 	private BCryptPasswordEncoder bcrypt;

	public User signUp(User user) throws InvalidUsernameException {
		User user1=authRepository.findByUsername(user.getUsername());
		if(user1 != null)
			throw new InvalidUsernameException("User Already Exist");
		
		if(user.getRole()==null)
			user.setRole("User Default");
		String encodedPass = bcrypt.encode(user.getPassword());
		user.setPassword(encodedPass);
		
		return authRepository.save(user);
	}

	public User getUserById(int userid) throws InvalidIDException {
		
		Optional<User> optional =  authRepository.findById(userid);
		if(optional.isEmpty())
			throw new InvalidIDException("User ID Not Exist!");
			
		return optional.get();
	}

}
