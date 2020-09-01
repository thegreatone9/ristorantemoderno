package com.tryout.backend.ristoranteEntity;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
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
