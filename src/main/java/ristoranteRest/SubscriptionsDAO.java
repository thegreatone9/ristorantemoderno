package ristoranteRest;

import java.util.List;

import ristoranteEntity.Dish;

public interface SubscriptionsDAO {
	
	public List<Dish> getAll();
	
	public Dish findById(int theId);
	
	public void add(Dish theDish);
	
	public void deleteById(int theId);
}
