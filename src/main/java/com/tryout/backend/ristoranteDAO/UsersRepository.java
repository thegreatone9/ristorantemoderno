package com.tryout.backend.ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tryout.backend.ristoranteEntity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

	//you get CRUD methods for free from Spring Data
}
