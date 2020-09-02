package com.tryout.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tryout.backend.ristoranteEntity.SignupRequest;
import com.tryout.backend.ristoranteRepository.RistoranteUserDAOImpl;

@RestController
public class SignupController {
	
	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:3000")
	public String signup(SignupRequest signupInfo) {
		String requestedUsername = signupInfo.getUsername();
		String requestedPassword = signupInfo.getPassword();
		if (checkForExistingAccount(requestedUsername)) {
			if (RistoranteUserDAOImpl.createNewUser(requestedUsername, requestedPassword)) return "Account created successfully!";
			else return "Error: There was an error creating your account.";
		}
			
		return "Error: Account with that email already exists. Please try another one.";		
	}
	
	public boolean checkForExistingAccount(String email) {
		if (RistoranteUserDAOImpl.selectRistoranteUserByUsername(email).isPresent()) 
			return true;	
		else 
			return false;
	}
}
