package com.tryout.backend.ristoranteEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {
	
	//field vars
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="author")
	private String author;
	
	//many comments can point to one dish
	@JoinColumn(name="dishid")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //these are the cascade effects required		
	private Dish dish; // Comment (the owner entity) has a join column dishid that stores the id value and has a foreign key to the Dish entity
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private String date;
	
	//constructors
	
	public Comment() {
		
	}


	public Comment(int dishid, String author, int rating, String comment, String date) {
		this.author = author;
		this.rating = rating;
		this.comment = comment;
		this.date = date;
		this.setDishId(dishid);
		
	}


	//getters and setters
	

	public int getDishId() {
		return dish.getId();
	}
	
	public void setDishId(int dishid) {
		dish.setId(dishid);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getauthor() {
		return author;
	}

	public void setauthor(String author) {
		this.author = author;
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
	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Dish getDish() {
		return dish;
	}


	public void setDish(Dish dish) {
		this.dish = dish;
	}

	//toString

	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", dish=" + dish + ", rating=" + rating + ", comment="
				+ comment + ", date=" + date + "]";
	}

}
