package com.tryout.backend.ristoranteDAO;

import java.util.List;

import com.tryout.backend.ristoranteEntity.Dish;

public interface DishesDAO {
	
	public List<Dish> getAll();
	
	public Dish findById(int theId);
	
	public void add(Dish theDish);
	
	public void deleteById(int theId);
}
