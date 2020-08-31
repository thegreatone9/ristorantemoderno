package com.tryout.backend.ristoranteService;

import java.util.List;

import com.tryout.backend.ristoranteEntity.Leader;

public interface LeadersService {

	public List<Leader> getAll();
	
	public Leader findById(int theId);
	
	public void add(Leader theLeader);
	
	public void deleteById(int theId);
}
