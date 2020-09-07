package com.tryout.backend.ristoranteRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tryout.backend.ristoranteEntity.Leader;

public interface LeadersRepository extends JpaRepository<Leader, Integer> {

	//you get CRUD methods for free from Spring Data
}
