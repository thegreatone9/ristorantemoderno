package com.tryout.backend.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class RistoranteUserDAOImpl implements RistoranteUserDAO{

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public RistoranteUserDAOImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	

	
	@Override
	public Optional<RistoranteUser> selectRistoranteUserByUsername(String username) {
		getRistoranteUsers();
		
		return null;
	}
	
	private List<RistoranteUser> getRistoranteUsers() {
		List<RistoranteUser> ristoranteUsers;
		return null;
	}

}
