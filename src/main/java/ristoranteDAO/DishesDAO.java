package ristoranteDAO;

import java.util.List;

import ristoranteEntity.Dish;

public interface DishesDAO {
	
	public List<Dish> getAll();
	
	public Dish findById(int theId);
	
	public void add(Dish theDish);
	
	public void deleteById(int theId);
}
