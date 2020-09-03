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
	
	//many comments can point to one dish, referenced by its corresponding column in this table "dishid"  ('dishid' is in this table!!!)
	@JoinColumn(name="dishid")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //these are the cascade effects required		
	private Dish dish; // Comment (the owner entity) has a join column dishid that stores the id value and has a foreign key to the Dish entity
	
	@Column(name="author")
	private String author;
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private String date;
	
	@Column(name="transferrableDishId")
	private int transferrableDishId;
	
	//constructors
	
	public Comment() {
		
	}


	//getters and setters
	
	
	
	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getTransferrableDishId() {
		return transferrableDishId;
	}


	public void setTransferrableDishId(int transferrableDishId) {
		this.transferrableDishId = transferrableDishId;
	}


	public void setAuthor(String author) {
		this.author = author;
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

	public Dish getDish() {
		return dish;
	}


	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	public String getAuthor() {
		return author;
	}


	public void setDish(String author) {
		this.author = author;
	}

	//toString
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", customer=" + customer + ", dish=" + dish + ", author=" + author + ", rating="
				+ rating + ", comment=" + comment + ", date=" + date + ", transferrableDishId=" + transferrableDishId
				+ "]";
	}
	

	
}
