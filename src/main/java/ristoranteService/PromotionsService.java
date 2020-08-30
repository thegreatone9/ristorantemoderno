package ristoranteService;

import java.util.List;

import ristoranteEntity.Promotion;

public interface PromotionsService {

	public List<Promotion> getAll();
	
	public Promotion findById(int theId);
	
	public void add(Promotion thePromotion);
	
	public void deleteById(int theId);
}
