package com.tryout.backend.ristoranteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryout.backend.ristoranteDAO.CommentsRepository;
import com.tryout.backend.ristoranteEntity.Comment;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	private CommentsRepository commentsRepository;
	
	@Autowired //qualifier given to identify which dao impl to use, as the context contains two beans corresponding to two impls 
	public CommentsServiceImpl(CommentsRepository theCommentsRepository) {
		this.commentsRepository = theCommentsRepository;
	}

	@Override
	public void deleteById(int theId) {
		
		commentsRepository.deleteById(theId);

	}

	@Override
	//@Transactional //org.springframework.Transactional update: no need with JPA repo, as its provided out of box
	public List<Comment> getAll() {
		
		return commentsRepository.findAll();
	}

	@Override
	public Comment findById(int theId) {
		
		//Optionals!!! because we may not find by that id
		Optional<Comment> result = commentsRepository.findById(theId);
		
		Comment theComment = null;
		
		if (result.isPresent()) {
			theComment = result.get();
		}
		
		else {
			throw new RuntimeException("Did not find comment id - " + theId);
		}
		
		return theComment;
	}

	@Override
	public void add(Comment theComment) {
		
		commentsRepository.save(theComment);
		
	}

}
