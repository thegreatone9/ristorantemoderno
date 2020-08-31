package com.tryout.backend.ristoranteService;

import java.util.List;

import com.tryout.backend.ristoranteEntity.Feedback;

public interface FeedbacksService {

	public List<Feedback> getAll();
	
	public Feedback findById(int theId);
	
	public void add(Feedback theFeedback);
	
	public void deleteById(int theId);
}
