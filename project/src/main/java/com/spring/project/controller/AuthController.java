package com.spring.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.config.JwtUtil;
import com.spring.project.dto.TokenDto;
import com.spring.project.exception.InvalidUsernameException;
import com.spring.project.model.User;
import com.spring.project.service.AuthService;
import com.spring.project.service.MyUserService;



@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	@Autowired
 	private MyUserService myUserService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUsernameException {
		
		return authService.signUp(user);
		
	}
	@PostMapping("/login")
	public UserDetails login(Principal principal) {
		String username=principal.getName();
		return myUserService.loadUserByUsername(username);
		
	}
	@PostMapping("/token/generate")
	public TokenDto generateToken(@RequestBody User user, TokenDto dto) {
		
		// get username and password and validating
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		authenticationManager.authenticate(auth);
		
		//generating token
		
		String token = jwtUtil.generateToken(user.getUsername());
		dto.setToken(token);
		dto.setUsername(user.getUsername());
		dto.setExpiry(jwtUtil.extractExpiration(token).toString());
		return dto;
		
	}
	
	@GetMapping("/user/details")
	public UserDetails getUserDetails(Principal principal) {
	String username=principal.getName();
	return myUserService.loadUserByUsername(username);
		
	}

}






















