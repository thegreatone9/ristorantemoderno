package com.tryout.backend.ristoranteEntity;

public class AuthenticationResponse {
	
	private final String jwt;
	private final int customerid;

	public AuthenticationResponse(String jwt, int customerid) {
		this.jwt = jwt;
		this.customerid = customerid;
	}

	public String getJwt() {
		return jwt;
	}

	public int getCustomerid() {
		return customerid;
	}

}
