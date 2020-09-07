package com.tryout.backend.ristoranteEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="customers")
public class Customer {
	
	//define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	//@OneToMany annotation is used to define the property in comments class that will be used to map the mappedBy variable. That's why we have a property named "author" in the Comments class:
	@OneToMany(mappedBy="customer", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //refers to the author property in the comments class, (i.e. the table which contains the foreign key)
	private List<Comment> comments; //mapped by=author refers to the author property in Comment class. Note that comments is not a column, but refers to the fact that a list of comments of this user is tracked by the userId column in the comments table.
	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="dish_customer", joinColumns=@JoinColumn(name="customerid"), inverseJoinColumns=@JoinColumn(name="dishid"))
	private List<Dish> subscriptions;
	
	
	//define constructors
	
	//no arg-constructor is required by hibernate
	public Customer() {
		
	}


	//define getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<Dish> getSubscriptions() {
		return subscriptions;
	}


	public void setSubscriptions(List<Dish> subscriptions) {
		this.subscriptions = subscriptions;
	}

	//define toString
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", comments=" + comments + ", subscriptions=" + subscriptions + "]";
	}

	
	
	
	
	
	
	
}
