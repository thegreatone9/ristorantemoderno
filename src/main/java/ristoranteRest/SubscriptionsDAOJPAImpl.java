package ristoranteRest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; //you imported this from the wrong library -_-

import ristoranteEntity.Dish;

@Repository
public class SubscriptionsDAOJPAImpl implements SubscriptionsDAO { //easy to have another impl of EmployeeDAO: using the JPAImpl
	
	//define field for entitymanager (it is auto created by spring boot)
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public SubscriptionsDAOJPAImpl (EntityManager theEntityManager) {
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
		
		//create a query: using JPA API.
		TypedQuery<Dish> theQuery = entityManager.createQuery("from Employee", Dish.class);
		
		
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
		
		//get the employee
		Dish theDish = currentSession.find(Dish.class, theId);
		
		return theDish;
	}

	@Override
	public void add(Dish theDish) {
		
		//save or update the employee (if id == 0 then save/insert, else update)
		Dish dbDish = entityManager.merge(theDish);
		
		//update with id from db ... so we can get generated id for save/insert
		theDish.setId(dbDish.getId());
		
	}

	@Override
	public void deleteById(int theId) {
		
		
		
	}
	
	

}
