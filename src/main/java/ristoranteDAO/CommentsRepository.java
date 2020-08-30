package ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import ristoranteEntity.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Integer> {

	//you get CRUD methods for free from Spring Data
}
