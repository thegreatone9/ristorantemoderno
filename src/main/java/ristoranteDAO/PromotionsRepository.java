package ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import ristoranteEntity.Promotion;

public interface PromotionsRepository extends JpaRepository<Promotion, Integer> {

	//you get CRUD methods for free from Spring Data
}
