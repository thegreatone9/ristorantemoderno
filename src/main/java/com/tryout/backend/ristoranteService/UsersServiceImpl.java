package com.tryout.backend.ristoranteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryout.backend.ristoranteDAO.UsersRepository;
import com.tryout.backend.ristoranteEntity.User;

@Service
public class UsersServiceImpl implements UsersService {
	
	private UsersRepository UsersRepository;
	
	@Autowired //qualifier given to identify which dao impl to use, as the context contains two beans corresponding to two impls 
	public UsersServiceImpl(UsersRepository theUsersRepository) {
		this.UsersRepository = theUsersRepository;
	}

	@Override
	public void deleteById(int theId) {
		
		UsersRepository.deleteById(theId);

	}

	@Override
	//@Transactional //org.springframework.Transactional update: no need with JPA repo, as its provided out of box
	public List<User> getAll() {
		
		return UsersRepository.findAll();
	}

	@Override
	public User findById(int theId) {
		
		//Optionals!!! because we may not find by that id
		Optional<User> result = UsersRepository.findById(theId);
		
		User theUser = null;
		
		if (result.isPresent()) {
			theUser = result.get();
		}
		
		else {
			throw new RuntimeException("Did not find User id - " + theId);
		}
		
		return theUser;
	}

	@Override
	public void add(User theUser) {
		
		UsersRepository.save(theUser);
		
	}

}
