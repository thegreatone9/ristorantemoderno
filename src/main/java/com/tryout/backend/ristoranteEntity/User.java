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
@Table(name="users")
public class User {
	
	//define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	//@Column(name="first_name")
	//private String firstName;
	//
	//@Column(name="last_name")
	//private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	//@OneToMany(mappedBy="users", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //refers to the userId property in the comments table, (i.e. the table which contains the foreign key)
	//private List<Comment> comments; //mapped by=userId refers to the userId field in Comment class. Note that comments is not a column, but refers to the fact that a list of comments of this user is tracked by the userId column in the comments table.
	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
			   CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="dish_user", joinColumns=@JoinColumn(name="userId"), inverseJoinColumns=@JoinColumn(name="dishId"))
	private List<Dish> dishes;
	
	
	//define constructors
	
	//no arg-constructor is required by hibernate
	public User() {
		
	}

	//id is auto generated so not in args
	public User(String email, String password) {
		//this.firstName = firstName;
		//this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	
	//define getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public String getFirstName() {
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
	}*/

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

	
	//define toString
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
