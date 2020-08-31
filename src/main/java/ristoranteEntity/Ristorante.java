package ristoranteEntity;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Ristorante {
	
	//fields
	private List<Subscription> subscriptions;
	private List<Dish> dishes;
	private List<Comment> comments;
	private List<Promotion> promotions;
	private List<Leader> leaders;
	private List<Feedback> feedback;
	
	
	//constructors
	
	public Ristorante() {
		
	}
	
	public Ristorante(List<Subscription> subscriptions, List<Dish> dishes, List<Comment> comments,
			List<Promotion> promotions, List<Leader> leaders, List<Feedback> feedback) {
		this.subscriptions = subscriptions;
		this.dishes = dishes;
		this.comments = comments;
		this.promotions = promotions;
		this.leaders = leaders;
		this.feedback = feedback;
	}

	
	//getters and setters

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Leader> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<Leader> leaders) {
		this.leaders = leaders;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	
	//toString
	
	@Override
	public String toString() {
		return "ristorante [subscriptions=" + subscriptions + ", dishes=" + dishes + ", comments=" + comments
				+ ", promotions=" + promotions + ", leaders=" + leaders + ", feedback=" + feedback + "]";
	}
	
	
	
}
