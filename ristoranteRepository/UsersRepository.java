package com.tryout.backend.ristoranteRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tryout.backend.ristoranteEntity.Customer;

public interface UsersRepository extends JpaRepository<Customer, Integer> {

	//you get CRUD methods for free from Spring Data
}
