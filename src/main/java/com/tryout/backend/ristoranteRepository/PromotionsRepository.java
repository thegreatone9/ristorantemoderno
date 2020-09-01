package com.tryout.backend.ristoranteRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tryout.backend.ristoranteEntity.Promotion;

public interface PromotionsRepository extends JpaRepository<Promotion, Integer> {

	//you get CRUD methods for free from Spring Data
}
