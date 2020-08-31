package com.tryout.backend.ristoranteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryout.backend.ristoranteDAO.DishesRepository;
import com.tryout.backend.ristoranteEntity.Dish;

@Service
public class DishesServiceImpl implements DishesService {
	
	private DishesRepository dishesRepository;
	
	@Autowired //qualifier given to identify which dao impl to use, as the context contains two beans corresponding to two impls 
	public DishesServiceImpl(DishesRepository theDishesRepository) {
		this.dishesRepository = theDishesRepository;
	}

	@Override
	public void deleteById(int theId) {
		
		dishesRepository.deleteById(theId);

	}

	@Override
	//@Transactional //org.springframework.Transactional update: no need with JPA repo, as its provided out of box
	public List<Dish> getAll() {
		
		return dishesRepository.findAll();
	}

	@Override
	public Dish findById(int theId) {
		
		//Optionals!!! because we may not find by that id
		Optional<Dish> result = dishesRepository.findById(theId);
		
		Dish theDish = null;
		
		if (result.isPresent()) {
			theDish = result.get();
		}
		
		else {
			throw new RuntimeException("Did not find dish id - " + theId);
		}
		
		return theDish;
	}

	@Override
	public void add(Dish theDish) {
		
		dishesRepository.save(theDish);
		
	}

}
