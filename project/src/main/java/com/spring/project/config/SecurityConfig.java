package com.spring.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.project.service.MyUserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
 	private MyUserService myUserService;
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		   .csrf(csrf->csrf.disable())
		   .authorizeHttpRequests((authorize) -> authorize
				   .requestMatchers("/api/auth/token/generate").permitAll()
				   .requestMatchers("/api/auth/user/details").authenticated()
				   .requestMatchers("/api/auth/signup").permitAll()
				   .requestMatchers("/api/auth/login").authenticated()
				   .requestMatchers("/api/medicalhistory/add").hasAuthority("PATIENT")
				   .requestMatchers("/api/patient/add/{userid}/{medicalhistoryid}").hasAuthority("PATIENT")
				   .requestMatchers("/api/doctor/add/{userid}").hasAuthority("DOCTOR")
				   .requestMatchers("/api/appointments/add/{patientid}/{doctorid}").permitAll()
				   .requestMatchers("/api/doctor/getallpatient/{doctorid}").hasAuthority("DOCTOR")

				   .anyRequest().authenticated()
				   )
		   .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		   
		return http.build();
		
	}
	
	@Bean
	AuthenticationProvider getAuth() {
 		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
 		dao.setPasswordEncoder(passEncoder());
 		dao.setUserDetailsService(myUserService);	
 		return dao;
	}
	
	@Bean
 	BCryptPasswordEncoder passEncoder(){
 		return new BCryptPasswordEncoder();
 	}
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		
		return auth.getAuthenticationManager();
		
	}

}
















