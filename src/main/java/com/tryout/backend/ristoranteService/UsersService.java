package com.tryout.backend.ristoranteService;

import java.util.List;

import com.tryout.backend.ristoranteEntity.User;

public interface UsersService {

	public List<User> getAll();
	
	public User findById(int theId);
	
	public void add(User theUser);
	
	public void deleteById(int theId);
}
