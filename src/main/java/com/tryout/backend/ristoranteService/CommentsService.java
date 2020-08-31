package com.tryout.backend.ristoranteService;

import java.util.List;

import com.tryout.backend.ristoranteEntity.Comment;

public interface CommentsService {

	public List<Comment> getAll();
	
	public Comment findById(int theId);
	
	public void add(Comment theComment);
	
	public void deleteById(int theId);
}
