package ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import ristoranteEntity.Leader;

public interface LeadersRepository extends JpaRepository<Leader, Integer> {

	//you get CRUD methods for free from Spring Data
}
