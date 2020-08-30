package ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import ristoranteEntity.Dish;

public interface DishesRepository extends JpaRepository<Dish, Integer> {

	//you get CRUD methods for free from Spring Data
}
