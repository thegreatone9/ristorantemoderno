package com.tryout.backend.ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tryout.backend.ristoranteEntity.Dish;

public interface DishesRepository extends JpaRepository<Dish, Integer> {

	//you get CRUD methods for free from Spring Data
}
