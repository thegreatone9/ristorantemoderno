package com.tryout.backend.ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tryout.backend.ristoranteEntity.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Integer> {

	//you get CRUD methods for free from Spring Data
}
