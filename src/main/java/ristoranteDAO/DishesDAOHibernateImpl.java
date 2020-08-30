package ristoranteDAO;

import java.util.List;

import javax.persistence.EntityManager;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; //you imported this from the wrong library -_-

import ristoranteEntity.Dish;

@Repository
public class DishesDAOHibernateImpl implements DishesDAO { //easy to have another impl of EmployeeDAO: using the JPAImpl
	
	//define field for entitymanager (it is auto created by spring boot)
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public DishesDAOHibernateImpl (EntityManager theEntityManager) {
		entityManager = theEntityManager; //you had missed this assignment -_-
	}

	@Override
	@Transactional //spring magically manages the sessions using this annotation
	public List<Dish> getAll() {
		
		System.out.println("inside findAll!!!!");
		System.out.println("inside findAll!!!!");
		System.out.println("inside findAll!!!!");
		System.out.println("inside findAll!!!!");
		//get the current hibernate session from the entity manager
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		System.out.println("unwrapped Session!!!!");
		System.out.println("unwrapped Session!!!!");
		System.out.println("unwrapped Session!!!!");
		System.out.println("unwrapped Session!!!!");
		
		//create a query: using native Hibernate API...if you were to use standard JPA API implementation instead, (you would use @Qualifier annotation to target it and then here) you would write: entityManager.createQuery();
		Query<Dish> theQuery = currentSession.createQuery("from Dishes", Dish.class);
		
		
		System.out.println("created query!!!!");
		
		//execute query and get result list
		List<Dish> employees = theQuery.getResultList();
		
		
		System.out.println("got results!!!!");
		
		//return the results
		return employees;
	}

	@Override
	public Dish findById(int theId) {

		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//get the dish
		Dish theDish = currentSession.get(Dish.class, theId);
		
		return theDish;
	}

	@Override
	public void add(Dish theDish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
	}
	
}
