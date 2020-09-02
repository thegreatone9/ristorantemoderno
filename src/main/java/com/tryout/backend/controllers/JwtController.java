package com.tryout.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tryout.backend.ristoranteEntity.AuthenticationRequest;
import com.tryout.backend.ristoranteEntity.AuthenticationResponse;
import com.tryout.backend.ristoranteEntity.RistoranteUser;
import com.tryout.backend.security.JwtUtil;

@RestController
public class JwtController {
	

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	@Qualifier("ristoranteModernoService")
	private UserDetailsService userDetailsService;
	
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		
		catch (BadCredentialsException e){
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final int customerid = ((RistoranteUser) userDetails).getCustomerId();
		System.out.println("Customer id is: " + customerid);
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		System.out.println("Jwt authentication passed. Jwt is: " + jwt);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt, customerid));
	}
	
	@RequestMapping(value="/rest_data", method = RequestMethod.GET)
	public String restData() {
		System.out.println("get request received!");
		return "here's your data: you are an idiot";
	}
}
