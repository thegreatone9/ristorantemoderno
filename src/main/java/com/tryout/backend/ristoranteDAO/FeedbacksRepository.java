package com.tryout.backend.ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tryout.backend.ristoranteEntity.Feedback;

public interface FeedbacksRepository extends JpaRepository<Feedback, Integer> {

	//you get CRUD methods for free from Spring Data
}
