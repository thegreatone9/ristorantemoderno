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
@Table(name="dishes")
public class Dish {
	
	//create field vars
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="category")
	private String category;
	
	@Column(name="label")
	private String label;
	
	@Column(name="price")
	private double price;
	
	@Column(name="featured")
	private boolean featured;
	
	@Column(name="description")
	private String description;
	
	@Column(name="servetime")
	private String serveTime;
	
	@OneToMany(mappedBy="dish", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //refers to the dish property in the comments class, (i.e. the table which contains the foreign key)
	private List<Comment> comments; //mapped by=dish refers to the dish property in Comment class. Note that comments is not a column, but refers to the fact that a list of comments of this dish is tracked by the dishid column in the comments table.
	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
			   CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="dish_customer", joinColumns=@JoinColumn(name="dishid"), inverseJoinColumns=@JoinColumn(name="customerid"))
	private List<Customer> customers;
	
	//constructor
	public Dish() {
		
	}

	
	//getters and setters
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

	public boolean getfeatured() {
		return featured;
	}


	public void setfeatured(boolean featured) {
		this.featured = featured;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getServeTime() {
		return serveTime;
	}

	public void setServeTime(String serveTime) {
		this.serveTime = serveTime;
	}

	//toString
	

	@Override
	public String toString() {
		return "Dishes [id=" + id + ", name=" + name + ", image=" + image + ", category=" + category + ", label="
				+ label + ", price=" + price + ", featured=" + featured + ", description=" + description + ", serveTime="
				+ serveTime + "]";
	}
	
}
