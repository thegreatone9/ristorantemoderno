package com.tryout.backend.ristoranteService;

import java.util.List;

import com.tryout.backend.ristoranteEntity.Promotion;

public interface PromotionsService {

	public List<Promotion> getAll();
	
	public Promotion findById(int theId);
	
	public void add(Promotion thePromotion);
	
	public void deleteById(int theId);
}
