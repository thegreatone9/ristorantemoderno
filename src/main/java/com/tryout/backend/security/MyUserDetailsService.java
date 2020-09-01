package com.tryout.backend.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//this service-impl is supposed to get data from customerDAO

@Service
public class MyUserDetailsService implements UserDetailsService {

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("admin", "thegreatone9", new ArrayList<>());
	}
}
