package com.tryout.backend.ristoranteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryout.backend.ristoranteDAO.FeedbacksRepository;
import com.tryout.backend.ristoranteEntity.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbacksService {
	
	private FeedbacksRepository FeedbacksRepository;
	
	@Autowired //qualifier given to identify which dao impl to use, as the context contains two beans corresponding to two impls 
	public FeedbackServiceImpl(FeedbacksRepository theFeedbacksRepository) {
		this.FeedbacksRepository = theFeedbacksRepository;
	}

	@Override
	public void deleteById(int theId) {
		
		FeedbacksRepository.deleteById(theId);

	}

	@Override
	//@Transactional //org.springframework.Transactional update: no need with JPA repo, as its provided out of box
	public List<Feedback> getAll() {
		
		return FeedbacksRepository.findAll();
	}

	@Override
	public Feedback findById(int theId) {
		
		//Optionals!!! because we may not find by that id
		Optional<Feedback> result = FeedbacksRepository.findById(theId);
		
		Feedback theFeedback = null;
		
		if (result.isPresent()) {
			theFeedback = result.get();
		}
		
		else {
			throw new RuntimeException("Did not find Feedback id - " + theId);
		}
		
		return theFeedback;
	}

	@Override
	public void add(Feedback theFeedback) {
		
		FeedbacksRepository.save(theFeedback);
		
	}

}
