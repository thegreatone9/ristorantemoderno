package com.tryout.backend.ristoranteEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	
	//field vars
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	//many comments can point to one customer, referenced by its corresponding representative's id in this table 'customer'. ('userid' is in this table!!!)
	@JoinColumn(name="customerid")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //these are the cascade effects required		
	private Customer customer;
	
	@Column(name="dishid")
	private int dishid; 
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private String date;
	
	//constructors
	
	public Comment() {
		
	}


	//getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public int getDishid() {
		return dishid;
	}


	public void setDishid(int dishid) {
		this.dishid = dishid;
	}

	//toString

	@Override
	public String toString() {
		return "Comment [id=" + id + ", customer=" + customer + ", dishid=" + dishid + ", rating=" + rating
				+ ", comment=" + comment + ", date=" + date + "]";
	}


	
}
