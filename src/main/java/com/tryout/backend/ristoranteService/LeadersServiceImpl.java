package com.tryout.backend.ristoranteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryout.backend.ristoranteDAO.LeadersRepository;
import com.tryout.backend.ristoranteEntity.Leader;

@Service
public class LeadersServiceImpl implements LeadersService {
	
	private LeadersRepository leadersRepository;
	
	@Autowired //qualifier given to identify which dao impl to use, as the context contains two beans corresponding to two impls 
	public LeadersServiceImpl(LeadersRepository theLeadersRepository) {
		this.leadersRepository = theLeadersRepository;
	}

	@Override
	public void deleteById(int theId) {
		
		leadersRepository.deleteById(theId);

	}

	@Override
	//@Transactional //org.springframework.Transactional update: no need with JPA repo, as its provided out of box
	public List<Leader> getAll() {
		
		return leadersRepository.findAll();
	}

	@Override
	public Leader findById(int theId) {
		
		//Optionals!!! because we may not find by that id
		Optional<Leader> result = leadersRepository.findById(theId);
		
		Leader theLeader = null;
		
		if (result.isPresent()) {
			theLeader = result.get();
		}
		
		else {
			throw new RuntimeException("Did not find leader id - " + theId);
		}
		
		return theLeader;
	}

	@Override
	public void add(Leader theLeader) {
		
		leadersRepository.save(theLeader);
		
	}

}
