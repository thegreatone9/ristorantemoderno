package ristoranteDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import ristoranteEntity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

	//you get CRUD methods for free from Spring Data
}
