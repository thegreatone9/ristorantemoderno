package ristoranteEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	//many comments can have one User
	@Column(name="userId")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //these are the cascade effects required	
	private User userId;
	
	
	//many comments can point to one dish
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //these are the cascade effects required		
	@Column(name="dishId")
	private int dishId;
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private String date;
	
	//constructors
	
	public Comment() {
		
	}


	public Comment(User userId, int dishId, int rating, String comment, String date) {
		this.userId = userId;
		this.dishId = dishId;
		this.rating = rating;
		this.comment = comment;
		this.date = date;
	}


	//getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
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


	
	//toString
	
	@Override
	public String toString() {
		return "Comments [id=" + id + ", userId=" + userId + ", dishId=" + dishId + ", rating=" + rating + ", comment="
				+ comment + ", date=" + date + "]";
	}

}
