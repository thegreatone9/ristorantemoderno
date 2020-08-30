package ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import ristoranteEntity.Feedback;

public interface FeedbacksRepository extends JpaRepository<Feedback, Integer> {

	//you get CRUD methods for free from Spring Data
}
