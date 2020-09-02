package com.tryout.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tryout.backend.ristoranteEntity.SignupRequest;
import com.tryout.backend.ristoranteRepository.RistoranteUserDAOImpl;

@RestController
public class SignupController {
	
	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:3000")
	public String signup(@RequestBody SignupRequest signupInfo) {
		String requestedUsername = signupInfo.getUsername();
		String requestedPassword = signupInfo.getPassword();
		String requestedFirstName = signupInfo.getFirstname();
		String requestedLastName = signupInfo.getLastname();
		
		System.out.println("Requested user name is: " + requestedUsername);
		
		//if the called function below returns false, we know there is no existing account with that name, so proceed to create a new account
		if (!RistoranteUserDAOImpl.checkIfUserExists(requestedUsername)) {
			if (RistoranteUserDAOImpl.createNewUser(requestedUsername, requestedPassword, requestedFirstName, requestedLastName)) return "Account created successfully!";
			else return "Error: There was an error creating your account.";
		}
			
		return "Error: Account with that email already exists. Please try another one.";		
	}
	
}
