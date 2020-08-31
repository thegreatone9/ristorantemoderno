package com.tryout.backend.ristoranteEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="leader")
public class Leader {

	//fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="abbr")
	private String abbr;
	
	@Column(name="featured")
	private boolean featured;
	
	@Column(name="description")
	private String description;
	
	
	//constructors
	
	public Leader() {
		
	}
	
	public Leader(String name, String image, String designation, String abbr, boolean featured, String description) {
		this.name = name;
		this.image = image;
		this.designation = designation;
		this.abbr = abbr;
		this.featured = featured;
		this.description = description;
	}


	//getters, setters

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


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getAbbr() {
		return abbr;
	}


	public void setAbbr(String abbr) {
		this.abbr = abbr;
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
		return "Leaders [id=" + id + ", name=" + name + ", image=" + image + ", designation=" + designation + ", abbr="
				+ abbr + ", featured=" + featured + ", description=" + description + "]";
	}
	
	
	
	
}
