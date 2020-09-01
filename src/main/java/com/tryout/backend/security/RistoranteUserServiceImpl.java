package com.tryout.backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RistoranteUserServiceImpl implements UserDetailsService {

	private final RistoranteUserDAO ristoranteUserDAO;
	
	public RistoranteUserServiceImpl(RistoranteUserDAO ristoranteUserDAO) {
		this.ristoranteUserDAO = ristoranteUserDAO;
	}
	
	//pass UserDetails (i.e. the representation of a user..it is a spring security built-in interface) from this method to authenticationmanagerbuilder
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return ristoranteUserDAO
				.selectRistoranteUserByUsername(username)
				.orElseThrow(() -> 
						new UsernameNotFoundException(String.format("Username %s not found", username)));
	}
	
	
}


