package com.tryout.backend.ristoranteEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="promotion")
public class Promotion {

	
	//fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="label")
	private String label;
	
	@Column(name="price")
	private double price;
	
	@Column(name="featured")
	private boolean featured;
	
	@Column(name="description")
	private String description;
	
	
	//constructors
	
	public Promotion() {
		
	}


	public Promotion(String name, String image, String label, double price, boolean featured, String description) {
		this.name = name;
		this.image = image;
		this.label = label;
		this.price = price;
		this.featured = featured;
		this.description = description;
	}
	
	
	//getters setters
	
	
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


	public boolean isFeatured() {
		return featured;
	}


	public void setFeatured(boolean featured) {
		this.featured = featured;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	//toString
	
	@Override
	public String toString() {
		return "Promotions [id=" + id + ", name=" + name + ", image=" + image + ", label=" + label + ", price=" + price
				+ ", featured=" + featured + ", description=" + description + "]";
	}

	
	
	
	
}
