package com.tryout.backend.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tryout.backend.ristoranteRepository.RistoranteUserDAOImpl;

@Service("ristoranteModernoService")
public class RistoranteUserServiceImpl implements UserDetailsService {

	private final RistoranteUserDAOImpl ristoranteUserDAO;
	
	public RistoranteUserServiceImpl(@Qualifier("ristoranteModernoDAO") RistoranteUserDAOImpl ristoranteUserDAO) {
		this.ristoranteUserDAO = ristoranteUserDAO;
	}
	
	//pass UserDetails (i.e. the representation of a user..it is a spring security built-in interface) from this method to authenticationmanagerbuilder
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return ristoranteUserDAO
				.selectRistoranteUserByUsername(email)
				.orElseThrow(() -> 
						new UsernameNotFoundException(String.format("Email %s not found", email)));
		
	}
	
	
}


